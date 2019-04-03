package com.syt.quartz.task;


import com.syt.utils.InitDatabaseUtils;
import com.syt.utils.Lg;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

@Component(value = "backDBTask")
public class BackDBTask {


    public void backUp(){
        Lg.log("备份数据库");
        try{
            Properties properties = new Properties();
            InputStream is = BackDBTask.class.getClassLoader().getResourceAsStream("backdb.properties");
            InputStreamReader isr = new InputStreamReader(is,"utf-8");
            properties.load(isr);
            String command = InitDatabaseUtils.getExportCommand(properties);
            String fileName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
            command = command + fileName + ".sql";
            Runtime runtime = Runtime.getRuntime();
            runtime.exec(command);
            Lg.log("备份数据库成功",properties.getProperty("jdbc.exportPath"));
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
