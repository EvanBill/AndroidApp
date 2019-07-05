package com.example.zhang.mvp.presenter;

import com.example.zhang.base.BasePresenter;
import com.example.zhang.mvp.contract.FileProviderContract;
import com.example.zhang.mvp.model.FileProviderModel;

public class FileProviderPresenter extends BasePresenter<FileProviderContract.IFileProviderContractView, FileProviderModel> {
    protected FileProviderPresenter(FileProviderContract.IFileProviderContractView view) {
        super(view);
        model = new FileProviderModel();
    }
}
