package com.esgi.androtopic.Data.Api.Services;

import com.esgi.androtopic.Data.Api.IServiceResultListener;
import com.esgi.androtopic.Data.Model.User;

/**
 * Created by kevin on 11/07/2017.
 */

public interface IUserService {

    void getUser(String token,IServiceResultListener<User> isrl);

}
