package com.esgi.androtopic.Data.Api.Services;

import com.esgi.androtopic.Data.Api.IServiceResultListener;
import com.esgi.androtopic.Data.Model.PostAuth;
import com.esgi.androtopic.Data.Model.PostSubscribe;

/**
 * Created by kevin on 28/06/2017.
 */

public interface IAuthService {

    public void login(PostAuth pa, IServiceResultListener<String> isrl);

    public void sign(PostSubscribe ps, IServiceResultListener<Void> isrl);
}
