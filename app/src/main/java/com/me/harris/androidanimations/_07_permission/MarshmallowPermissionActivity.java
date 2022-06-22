package com.me.harris.androidanimations._07_permission;

import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import android.view.View;

import com.me.harris.androidanimations.BaseAppCompatActivity;
import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.ActivityPermissionBinding;
import com.me.harris.androidanimations.interfaces.ActionCallBack;

import static android.Manifest.permission.READ_CALENDAR;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;

/**
 * Created by Harris on 2016/10/10.
 */

public class MarshmallowPermissionActivity extends BaseAppCompatActivity {
    ActivityPermissionBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_permission);
//        if (ActivityCompat.checkSelfPermission(this,P))
//        requestPermissions();
//        onRequestPermissionsResult();
//        shouldShowRequestPermissionRationale()
        binding.setCallback(new ActionCallBack() {
            @Override
            public void onClickView(View view) {
                Intent intent = new Intent(MarshmallowPermissionActivity.this, CameraPreviewActivity.class);
                startActivity(intent);
            }
        });

    }

    public static final int REQUEST_CALENDAR = 1;

    private void showEvents() {
        if (ActivityCompat.checkSelfPermission(this, READ_CALENDAR) != PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, READ_CALENDAR)) {
                showCalendarPermissionRequestRationale();
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{READ_CALENDAR}, REQUEST_CALENDAR);
            }
        } else {
            //已经拥有permission
        }
    }

    private void showCalendarPermissionRequestRationale() {
        //do something tell the user why you need this permission
    }

    /**
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        PermissionChecker.checkCallingPermission()
// 用于IPC，若用户grant了本应用CONTACT PERMISSION , 但别的app想要通过本应用获取CONTACT_PERMISSION
        //需要检查下对方是否具有权限，因为对方可能是个legacy app，
        // 而用户device为23，用户关闭了对方app的CONTACT
        //权限
        if (requestCode == REQUEST_CALENDAR) {
            // Empty Permission and result array means a cancellation
            if (grantResults.length == 0) {
                return;
            }
            if (grantResults[0] == PERMISSION_GRANTED) {
                // do something with this permission
            }
        }
    }

    /*
    * Permission分为normal permission和dangerous permission
    * 在 API 23上, normal permission会被立即给予，不会打扰用户，只有dangerous permission需要请求
    * dangerous permission 包括 CONTACT,CAMERA,LOCATIONS等等
    * https://developer.android.com/guide/topics/security/permissions.html#normal-dangerous
    *
    * 还有需要注意的一点是，permission是按照group来给的，如果用户给了READ_CONTACT permission，
    * 下次请求WRITE_CONTACT permission，系统立即给permission
    * 但是，不是说就不需要请求了，还得请求，只是会立即给，因为future release可能会改掉，
    *
    * 请求dangerous permission会弹出一个dialog，这个dialog开发者无权定制
    * */

    /*
    *
    * 用户可以在setting里面关闭permission
    * 对于TargetSdk 22及以下的app
    * 运行在M设备上时，
    * Apps expect always granted permissions
    * Disable APIs instead of revoke permissions
    * Every permission has an app-op
    * Disable app-op makes an API a no-op
    * 例如，假设App target sdk 为22
    * 用户运行在API 23的手机上，用户在SETTING取消App CONTACT PERMISSION.
    * 此时请求CONTACTS，android会返回空数据
    * 你可能以为手机上没有联系人，但实际上是有的
    * 只是对legacy app返回了空数据
    * 这样就做到了对于Legacy app 和Modern app一视同仁
    * 简单说对于legacy app,用户如果在setting里面disable permission了，系统会disable api，
    * 不同的API会有不同的返回值，有的是default error，有的是empty array
    * 测试下来Camera会抛出一个Exception(App target 22, 模拟器23，设置里关闭camera 权限 )
    * 这样，绝大多数的Legacy app不需要针对API 23的手机做太多变动，除非用户取消了特定权限
    * 所以比较好的方式还是 target sdk 23 above ，
    * */

   /* 如何避免请求过多的permission
   Kitkat 开始加入三个API,在SD卡上模拟出专属App的一块存储区域
   *  Context.getExternalFilesDirs[String type]
   *  Context.getExternalCacheDirs()
   *  Context.getExternalMediaDirs()
   *
   *  返回值,这些路径存储在SD卡上，且针对应用完全开放读写权限，不许permission
   *  卸载该应用后，这些路径里的文件全部会被清除
   *  在setting里面可以选择清除缓存，这里缓存的大小包括了这些SD卡上路径文件的大小
   *  /storage/emulated/Android/data/com.example.myapp/files/
   *  /storage/emulated/Android/media/com.example.myapp/
   * */

    /*Storage Access Framework  Kitkat加入
    允许用户自主选择文件 而不是请求整个SD卡的权限
    发起一个intent就可以了
    Intent.ACTION_OPEN_DOCUMENT  "open file"
    Intent.ACTION_CREATE_DOCUMENT "create file"
    Intent.ACTION_GET_CONTENT  "attach file"

    V4包里有FileProvider
    Intent intent = new Intent(Intent.ACTION_SEND);
    intent.setData(FileProvider.getUriForFile(context
            ,myAuthority,myFile));
    intent.addFlag(Intent.FLAG_GRANT_READ_URI_PERMISSION);
    //暂时将这个uri提供给接收方，暂时的，对方可能不有权限，
    //但对方在接收到uri还会有该权限
        )
    * */

    /*
    READ_PHONE_STATE
    IMEI作为设备唯一标示不如使用java 的UUID，比如平板就不会有IMEI
    CALL_PHONE 会主动按下拨号键，最好使用ACTION_DIAL，只会填入号码，等待用户拨号
    *
    * 例如
    * Intent intent = new Intent(Intent.ACTION_DIAL);
    * intent.setData(
    *   Uri.fromParts("tel","800-555-0199",null));
    * startActivity(intent); //launch dial activity
    * */

    /*
    * CAMERA  MICROPHONE permission不必要
    *  使用MediaStore.ACTION_IMAGE_CAPTURE
    *      MediaStore.ACTION_VIDEO_CAPTURE
    *  确保可以有权限
    *
    *  这里注意，如果App试图先尝试CAMERA PERMISSION,但被用户拒绝
    *  随后尝试使用上述intent ,系统会throw securityException
    *  所以，要么请求完整的camera permission，要么使用intent
    * */

    /* READ_CONTACTS   WRITE_CONTACTS
    * Intent intent = new Intent(Intent.ACTION_PICK);
    * intent.setDat(ContactsContract.Contacts.CONTENT_URI);
    * startActivityForResult(intent,42);
    *用户选择了一个Contact，暂时获得这个Contact的权限
    *
    * Intent intent = new Intent(ContactsContract.Intents.
                SHOW_OR_CREATE_CONTACT);
        intent.setData(Uri.fromParts("tel", "800-555-0199", null));
        intent.putExtra(ContactsContract.Intents.Insert.NAME, "Bob");
        intent.putExtra(ContactsContract.Intents.Insert.EMAIL, "Bob@example.com");
        startActivity(intent);
    *
    *
    *
    * */

    /*
    * calendar_provider有类似的intent
    * */

    private void addInformationToUserContact() {
        Intent intent = new Intent(ContactsContract.Intents.
                SHOW_OR_CREATE_CONTACT);
        intent.setData(Uri.fromParts("tel", "800-555-0199", null));
        intent.putExtra(ContactsContract.Intents.Insert.NAME, "Bob");
        intent.putExtra(ContactsContract.Intents.Insert.EMAIL, "Bob@example.com");
        startActivity(intent);
    }
}
