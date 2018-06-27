package zhuazhu.science.data.db;

import com.raizlabs.android.dbflow.annotation.Database;

import static zhuazhu.science.data.db.ScienceDataBase.NAME;
import static zhuazhu.science.data.db.ScienceDataBase.VERSION;

/**
 * @author zhuazhu
 **/
@Database(version = VERSION,name = NAME)
public class ScienceDataBase {
    public static final int VERSION = 1;
    public static final String NAME = "science";
}
