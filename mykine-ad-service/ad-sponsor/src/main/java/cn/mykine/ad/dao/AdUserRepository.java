package cn.mykine.ad.dao;

import cn.mykine.ad.entity.AdUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Jo@mykine
 */
public interface AdUserRepository extends JpaRepository<AdUser, Long> {

    /**
     * <h2>根据用户名查找用户记录</h2>
     * */
    AdUser findByUsername(String username);
}
