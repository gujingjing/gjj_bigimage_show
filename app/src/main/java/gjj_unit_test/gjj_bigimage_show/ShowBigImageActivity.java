package gjj_unit_test.gjj_bigimage_show;

import android.app.Activity;
import android.os.Bundle;

import java.io.IOException;
import java.io.InputStream;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：gjj on 2016/2/24 15:30
 * 邮箱：Gujj512@163.com
 */
public class ShowBigImageActivity extends Activity {
    @Bind(R.id.iv_larg)
    LargeImageView ivLarg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_big_image);
        ButterKnife.bind(this);

        try
        {
            InputStream inputStream = getResources().getAssets().open("qm.jpg");
            ivLarg.setInputStream(inputStream);

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
