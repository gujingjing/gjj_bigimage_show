package gjj_unit_test.gjj_bigimage_show;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.iv_big)
    ImageView ivBig;
    @Bind(R.id.iv_big_real)
    ImageView ivBigReal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        try {
            InputStream inputStream = getResources().getAssets().open("tangyan.jpg");//获取assets下的图片

            /*显示原来的图片*/
            Bitmap bitmap1 = BitmapFactory.decodeStream(inputStream);
            ivBigReal.setImageBitmap(bitmap1);
            /*显示处理过的图片*/
            //获取图片的宽高
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;//如果我们把它设为true，那么BitmapFactory.decodeFile(String path, Options opt)并不会真的返回一个Bitmap给你，它仅仅会把它的宽，高取回来给你
            BitmapFactory.decodeStream(inputStream, null, options);
            int width = options.outWidth;
            int height = options.outHeight;
            //设置显示图片的中心区域
            BitmapRegionDecoder bitmapRegionDecoder = BitmapRegionDecoder.newInstance(inputStream, false);
            BitmapFactory.Options options1 = new BitmapFactory.Options();
            options1.inPreferredConfig = Bitmap.Config.RGB_565;//色彩的存储方式
            //获取到图片的一部分----中心的100*100的区域
            Bitmap bitmap = bitmapRegionDecoder.decodeRegion(new Rect(width / 2 - 100, height / 2 - 100, width / 2 + 100, height / 2 + 100), options1);
            ivBig.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @OnClick({R.id.btn_to})void onclick(View view){
        switch (view.getId()){
            case R.id.btn_to://跳转加载大图
                Intent intent=new Intent(MainActivity.this,ShowBigImageActivity.class);
                startActivity(intent);
                break;
        }
    }
}
