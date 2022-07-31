package cn.mykine.ad.service.impl;

import cn.mykine.ad.dao.CreativeRepository;
import cn.mykine.ad.entity.Creative;
import cn.mykine.ad.service.ICreativeService;
import cn.mykine.ad.vo.CreativeRequest;
import cn.mykine.ad.vo.CreativeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Jo@mykine
 */
@Service
public class CreativeServiceImpl implements ICreativeService {

    private final CreativeRepository creativeRepository;

    @Autowired
    public CreativeServiceImpl(CreativeRepository creativeRepository) {
        this.creativeRepository = creativeRepository;
    }

    @Override
    public CreativeResponse createCreative(CreativeRequest request) {

        Creative creative = creativeRepository.save(
                request.convertToEntity()
        );

        return new CreativeResponse(creative.getId(), creative.getName());
    }
}
