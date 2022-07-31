package cn.mykine.ad.service;

import cn.mykine.ad.exception.AdException;
import cn.mykine.ad.vo.CreateUserRequest;
import cn.mykine.ad.vo.CreateUserResponse;

/**
 * Created by Jo@mykine
 */
public interface IUserService {

    /**
     * <h2>创建用户</h2>
     * */
    CreateUserResponse createUser(CreateUserRequest request)
            throws AdException;
}
