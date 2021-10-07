package cn.tnar.msf.cm4.msg.dto.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.tnar.msf.cm4.msg.dto.dao.TThirdAdmissionRelationMapper;
import cn.tnar.msf.cm4.msg.dto.entity.ThirdCarInRelation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TThirdAdmissionRelationServiceImpl extends ServiceImpl<TThirdAdmissionRelationMapper, ThirdCarInRelation>
        implements ThirdCarInRelationService {

    @Transactional
    @Override
    public void insert() {
        ThirdCarInRelation record1 = new ThirdCarInRelation();
        record1.setParkCode("1");
        record1.setSerialno("1");
        record1.setRemoteId("1");
        record1.setCreateTime(0L);
        record1.setUpdateTime(0L);
        this.save(record1);
        ThirdCarInRelation record2 = new ThirdCarInRelation();
        record2.setParkCode("1");
        record2.setSerialno("1");
        record2.setRemoteId("2");
        record2.setCreateTime(0L);
        record2.setUpdateTime(0L);
        this.save(record2);
//        throw new RuntimeException("xnzcmznxcm");
    }
}




