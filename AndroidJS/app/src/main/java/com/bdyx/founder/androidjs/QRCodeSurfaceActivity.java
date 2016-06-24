package com.bdyx.founder.androidjs;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.ViewGroup;

import com.bdyx.founder.androidjs.utils.DisplayUtil;
import com.bdyx.founder.androidjs.views.CameraInterface;
import com.bdyx.founder.androidjs.views.CameraSurfaceView;

/**
 * Created by Administrator on 2016/6/24 0024.
 */
public class QRCodeSurfaceActivity extends Activity implements CameraInterface.CamOpenOverCallback {
    /**
     * surfaceView
     */
    private CameraSurfaceView sfvCamera = null;
    float previewRate = -1f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface_qrcode);
        initView();
        dealView();
        openCamera();
        initViewParams();
    }

    private void initView() {
        sfvCamera = (CameraSurfaceView) findViewById(R.id.sfv_camera);
    }

    private void dealView() {

    }

    /**
     * 打开相机
     */
    private void openCamera(){
        Thread openThread = new Thread(){
            @Override
            public void run() {
                // TODO Auto-generated method stub
                CameraInterface.getInstance().doOpenCamera(QRCodeSurfaceActivity.this);
            }
        };
        openThread.start();
    }

    @Override
    public void cameraHasOpened() {
        SurfaceHolder holder = sfvCamera.getSurfaceHolder();
        CameraInterface.getInstance().doStartPreview(holder, previewRate);
    }

    private void initViewParams(){
        ViewGroup.LayoutParams params = sfvCamera.getLayoutParams();
        Point p = DisplayUtil.getScreenMetrics(this);
        params.width = p.x;
        params.height = p.y;
        previewRate = DisplayUtil.getScreenRate(this); //默认全屏的比例预览
        sfvCamera.setLayoutParams(params);

    }

}
