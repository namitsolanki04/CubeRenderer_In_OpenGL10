package com.example.user.myapplication2;

import android.opengl.GLSurfaceView;
import android.util.Log;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by user on 8/30/2015.
 */
public class SquareRenderer implements GLSurfaceView.Renderer{
    public SquareRenderer(boolean useTransclucentBackground)
    {
        mTranslucentBackgroung=useTransclucentBackground;
        mSquare=new Square();
    }
    public void onDrawFrame(GL10 gl)
    {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl.glClearColor(0.0f, 0.5f, 0.5f, 1.0f);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslatef(0.0f, (float) Math.sin(mTransY), -20.0f);
        gl.glRotatef(mAngle, 0.0f, 1.0f, 0.0f);
        gl.glRotatef(mAngle, 1.0f, 0.0f, 0.0f);
       // gl.glScalef(1,2,1);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        mSquare.draw(gl);
       mTransY+=0.075f;
        mAngle+=0.4f;

    }

    public void onSurfaceChanged(GL10 gl,int width,int height)
    {
        Log.e("namit","onSurfaceChnaged width ="+width+"  height= "+height);
        gl.glViewport(0, 0, width, height);
        float ratio = (float) width/height;
        float aspectRatio ;
        float zNear=0.1f;
        float zfar=1000;
        float fieldOfView = 10.0f/57.3f;
        float size;
        gl.glEnable(GL10.GL_NORMALIZE);
        aspectRatio = (float) width /(float)height;

        gl.glMatrixMode(GL10.GL_PROJECTION);
        size= zNear * (float)(Math.tan((double)(fieldOfView/2.0f)));

        gl.glFrustumf(-size,size,-size/aspectRatio,size/aspectRatio,zNear,zfar);

        gl.glMatrixMode(GL10.GL_MODELVIEW);
    }

    public void onSurfaceCreated(GL10 gl,EGLConfig config)
    {
        gl.glCullFace(GL10.GL_FRONT);
        gl.glDisable(GL10.GL_DITHER);
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT,GL10.GL_FASTEST);
        if(mTranslucentBackgroung)
        {
            gl.glClearColor(0, 0, 0, 0);
        }
        else
        {
            gl.glClearColor(1,1,1,1);
        }
        gl.glEnable(GL10.GL_CULL_FACE);
        gl.glShadeModel(GL10.GL_SMOOTH);
        gl.glEnable(GL10.GL_DEPTH_TEST);
    }
    private boolean mTranslucentBackgroung;
    private Square mSquare;
    private float mTransY;
    private float mAngle;
}
