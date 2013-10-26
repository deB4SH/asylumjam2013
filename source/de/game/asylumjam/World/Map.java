package de.game.asylumjam.World;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.GdxRuntimeException;
import de.game.asylumjam.Misc.GLOBAL;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import java.util.ArrayList;

public class Map {

    Texture rock, rockNormals;
    public static ShaderProgram shader;

    //our constants...
    public static final float DEFAULT_LIGHT_Z = .0065f;
    public static final float AMBIENT_INTENSITY = 0.025f;
    public static final float LIGHT_INTENSITY = .75f;

    public static final Vector3 LIGHT_POS = new Vector3(0f,0f,DEFAULT_LIGHT_Z);

    //Light RGB and intensity (alpha)
    public static final Vector3 LIGHT_COLOR = new Vector3(1f, 0.8f, 0.6f);

    //Ambient RGB and intensity (alpha)
    public static final Vector3 AMBIENT_COLOR = new Vector3(0.6f, 0.6f, 1f);

    //Attenuation coefficients for light falloff
    public static final Vector3 FALLOFF = new Vector3(.4f, .4f, 200f);

    String VERT;

    //no changes except for LOWP for color values
    //we would store this in a file for increased readability
    String FRAG;

    private final int width = 25;
    private final int height = 25;

    private TMXReader mapReader;

    private ArrayList<Texture> textures = new ArrayList<Texture>();

    public Map(){

        rock = new Texture("Texturen/Licht1.png");
        rockNormals = new Texture("Texturen/Licht2.png");

        ShaderProgram.pedantic = false;
        createShader();

        //handle mouse wheel by inputlistener
        Gdx.input.setInputProcessor(new InputAdapter() {
            public boolean scrolled(int delta) {
                //LibGDX mouse wheel is inverted compared to lwjgl-basics
                LIGHT_POS.z = Math.max(0f, LIGHT_POS.z - (delta * 0.005f));
                System.out.println("New light Z: "+LIGHT_POS.z);
                return true;
            }
        });

        mapReader = new TMXReader(Gdx.files.internal(GLOBAL.MapTest).toString());

        loadTileData();
    }

    public void render(SpriteBatch spriteBatch){

        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        //reset light Z
        if (Gdx.input.isTouched()) {
            LIGHT_POS.z = DEFAULT_LIGHT_Z;
            System.out.println("New light Z: "+LIGHT_POS.z);
        }
        spriteBatch.setShader(shader);
        spriteBatch.begin();

        //send a Vector4f to GLSL
        shader.setUniformf("LightPos", LIGHT_POS);

        //bind normal map to texture unit 1
        rockNormals.bind(1);


        //bind diffuse color to texture unit 0
        //important that we specify 0 otherwise we'll still be bound to glActiveTexture(GL_TEXTURE1)
        rock.bind(0);

        for(int i=0; i < width; i++)
        {
            for(int j=0; j < height; j++)
            {
                spriteBatch.draw(textures.get(mapReader.getMapLayer().get(0).getSingleTile(i,j)),i*32,j*32);
            }
        }
        spriteBatch.end();
    }

    public void loadTileData()
    {
        try
        {   // 0
            textures.add(new Texture("Texturen/black_tile.png"));

            //1 +
            textures.add(new Texture("Texturen/floor.png"));
            textures.add(new Texture("Texturen/door_vertical.png"));
            textures.add(new Texture("Texturen/wall.png"));
            textures.add(new Texture("Texturen/black_tile.png"));

            textures.add(new Texture("Texturen/wall.png"));
            textures.add(new Texture("Texturen/door_horizontal.png"));
            textures.add(new Texture("Texturen/black_tile.png"));
            textures.add(new Texture("Texturen/black_tile.png"));

            textures.add(new Texture("Texturen/wall.png"));
            textures.add(new Texture("Texturen/black_tile.png"));
            textures.add(new Texture("Texturen/black_tile.png"));
            textures.add(new Texture("Texturen/black_tile.png"));

            textures.add(new Texture("Texturen/floor.png"));
            textures.add(new Texture("Texturen/black_tile.png"));
            textures.add(new Texture("Texturen/black_tile.png"));
            textures.add(new Texture("Texturen/black_tile.png"));

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public ArrayList<Layer> getMap()
    {
        return this.mapReader.getMapLayer();
    }

    private void createShader()
    {
        VERT = "attribute vec4 "+ShaderProgram.POSITION_ATTRIBUTE+";\n" +
                "attribute vec4 "+ShaderProgram.COLOR_ATTRIBUTE+";\n" +
                "attribute vec2 "+ShaderProgram.TEXCOORD_ATTRIBUTE+"0;\n" +

                "uniform mat4 u_projTrans;\n" +
                " \n" +
                "varying vec4 vColor;\n" +
                "varying vec2 vTexCoord;\n" +

                "void main() {\n" +
                "	vColor = "+ShaderProgram.COLOR_ATTRIBUTE+";\n" +
                "	vTexCoord = "+ShaderProgram.TEXCOORD_ATTRIBUTE+"0;\n" +
                "	gl_Position =  u_projTrans * " + ShaderProgram.POSITION_ATTRIBUTE + ";\n" +
                "}";

        FRAG =
                //GL ES specific stuff
                "#ifdef GL_ES\n" //
                        + "#define LOWP lowp\n" //
                        + "precision mediump float;\n" //
                        + "#else\n" //
                        + "#define LOWP \n" //
                        + "#endif\n" + //

                        "//attributes from vertex shader\n" +
                        "varying LOWP vec4 vColor;\n" +
                        "varying vec2 vTexCoord;\n" +
                        "\n" +

                        "//our texture samplers\n" +
                        "uniform sampler2D u_texture;   //diffuse map\n" +
                        "uniform sampler2D u_normals;   //normal map\n" +
                        "\n" +

                        "//values used for shading algorithm...\n" +
                        "uniform vec2 Resolution;         //resolution of screen\n" +
                        "uniform vec3 LightPos;           //light position, normalized\n" +
                        "uniform LOWP vec4 LightColor;    //light RGBA -- alpha is intensity\n" +
                        "uniform LOWP vec4 AmbientColor;  //ambient RGBA -- alpha is intensity \n" +
                        "uniform vec3 Falloff;            //attenuation coefficients\n" +
                        "\n" +

                        "void main() {\n" +
                        "	//RGBA of our diffuse color\n" +
                        "	vec4 DiffuseColor = texture2D(u_texture, vTexCoord);\n" +
                        "	\n" +
                        "	//RGB of our normal map\n" +
                        "	vec3 NormalMap = texture2D(u_normals, vTexCoord).rgb;\n" +
                        "	\n" +
                        "	//The delta position of light\n" +
                        "	vec3 LightDir = vec3(LightPos.xy - (gl_FragCoord.xy / Resolution.xy), LightPos.z);\n" +
                        "	\n" +
                        "	//Correct for aspect ratio\n" +
                        "	LightDir.x *= Resolution.x / Resolution.y;\n" +
                        "	\n" +
                        "	//Determine distance (used for attenuation) BEFORE we normalize our LightDir\n" +
                        "	float D = length(LightDir);\n" +
                        "	\n" +
                        "	//normalize our vectors\n" +
                        "	vec3 N = normalize(NormalMap * 2.0 - 1.0);\n" +
                        "	vec3 L = normalize(LightDir);\n" +
                        "	\n" +
                        "	//Pre-multiply light color with intensity\n" +
                        "	//Then perform \"N dot L\" to determine our diffuse term\n" +
                        "	vec3 Diffuse = (LightColor.rgb * LightColor.a) * max(dot(N, L), 0.0);\n" +
                        "\n" +
                        "	//pre-multiply ambient color with intensity\n" +
                        "	vec3 Ambient = AmbientColor.rgb * AmbientColor.a;\n" +
                        "	\n" +
                        "	//calculate attenuation\n" +
                        "	float Attenuation = 1.0 / ( Falloff.x + (Falloff.y*D) + (Falloff.z*D*D) );\n" +
                        "	\n" +
                        "	//the calculation which brings it all together\n" +
                        "	vec3 Intensity = Ambient + Diffuse * Attenuation;\n" +
                        "	vec3 FinalColor = DiffuseColor.rgb * Intensity;\n" +
                        "	gl_FragColor = vColor * vec4(FinalColor, DiffuseColor.a);\n" +
                        "}";

        shader = new ShaderProgram(VERT, FRAG);
        //ensure it compiled
        if (!shader.isCompiled())
            throw new GdxRuntimeException("Could not compile shader: "+shader.getLog());
        //print any warnings
        if (shader.getLog().length()!=0)
            System.out.println(shader.getLog());

        //setup default uniforms
        shader.begin();

        //our normal map
        shader.setUniformi("u_normals", 1); //GL_TEXTURE1

        shader.setUniformf("Resolution", Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        //light/ambient colors
        //LibGDX doesn't have Vector4 class at the moment, so we pass them individually...
        shader.setUniformf("LightColor", LIGHT_COLOR.x, LIGHT_COLOR.y, LIGHT_COLOR.z, LIGHT_INTENSITY);
        shader.setUniformf("AmbientColor", AMBIENT_COLOR.x, AMBIENT_COLOR.y, AMBIENT_COLOR.z, AMBIENT_INTENSITY);
        shader.setUniformf("Falloff", FALLOFF);

        //LibGDX likes us to end the shader program
        shader.end();
    }

}
