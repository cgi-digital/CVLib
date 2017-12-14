package dw.quickstarts.dao;

import dw.quickstarts.PhoneNumber;
import dw.quickstarts.dao.mappers.PhoneNumMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

/**
 * Created by callumbarnes on 14/12/2017.
 */
@RegisterMapper(PhoneNumMapper.class)
public interface PhoneNumDAO {

    @SqlQuery("select * from phonenumber where userid = :userid")
    List<PhoneNumber> findPhoneNumbersByUserID(@Bind("userid") Long userid);

}
