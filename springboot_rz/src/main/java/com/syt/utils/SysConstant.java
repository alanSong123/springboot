package com.syt.utils;

public class SysConstant {

    public static final String CAPTCHA_KEY="code";
    public static final String JOB_KEY_PREFIX="myJob_";
    public static final String TRIGGER_KEY_PREFIX="myTrigger_";
    public static final String SCHEDULE_DATA_KEY="schedule_data_key_";


    public enum ScheduleStates{
        NOMAL((byte)0)
        ,PAUSE((byte)1);

        private byte value;
        ScheduleStates(byte value){
            this.value = value;
        }

        public byte getValue(){
            return value;
        }

    }


}
