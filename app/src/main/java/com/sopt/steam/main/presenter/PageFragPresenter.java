package com.sopt.steam.main.presenter;

import com.sopt.steam.main.model.RecModel;
import com.squareup.okhttp.RequestBody;

/**
 * Created by Min_Mac on 16. 5. 3..
 */
public interface PageFragPresenter {

    void postToServer(RecModel recModel, RequestBody recData);
}
