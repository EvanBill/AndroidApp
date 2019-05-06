package com.example.zhang.client;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.myaidlserviceapp.Book;
import com.example.myaidlserviceapp.IMyAidlInterface;
import com.example.zhang.R;
import com.example.zhang.base.BaseActivity;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author zzh
 */
public class AidlClientActivity extends BaseActivity {
    @BindView(R.id.tv_aidl_client)
    TextView tvAidlClient;
    private IMyAidlInterface iMyAidlInterface;
    private ServiceConnection serviceConnection;

    public AidlClientActivity() {
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl_client);
        ButterKnife.bind(this);
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.example.myaidlserviceapp", "com.example.myaidlserviceapp.RemoteService"));
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }

    @OnClick({R.id.btn_aidl_client})
    void onClick(View view) {
        if (view.getId() == R.id.btn_aidl_client) {
            Random random = new Random();
            Book book = new Book("abc" + random.nextInt(10));
            try {
                iMyAidlInterface.addBook(book);
                tvAidlClient.setText(iMyAidlInterface.getBookList().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
