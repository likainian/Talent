package com.irenshi.personneltreasure.util;

import com.irenshi.atalent.ui.router.HomeRouter;
import com.irenshi.personneltreasure.bean.UnreadItemEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Set;

/**
 * Created by mike.li on 2018/6/5.
 */

public class UnReadCountCache extends Observable{
    private UnReadCountCache(){}
    private static UnReadCountCache unReadCountCache;
    public static UnReadCountCache getInstance(){
        if(unReadCountCache==null){
            unReadCountCache = new UnReadCountCache();
        }
        return unReadCountCache;
    }
    public static final String home = "home";
    public static final String backlog = "backlog";
    public static final String apply = "apply";
    public static final String application = "application";
    public static final String me = "me";

    public static final String company_im = "company_im";
    //{"response":"OK","responseCode":"0","unreadList":[
    public static final String companyStandard = "companyStandard";
    // {"name":"companyStandard","number":"1"},
    public static final String companyNotify = "companyNotify";
    // {"name":"companyNotify","number":"3"},
    public static final String questionnaire = "questionnaire";
    // {"name":"questionnaire","number":"0"},
    public static final String myMessage = "myMessage";
    // {"name":"myMessage","number":"0"},
    // {"name":"salary","number":"0"},
    // {"name":"vacationApprove","number":"7"},
    // {"name":"overtimeApprove","number":"6"},
    // {"name":"evectionApprove","number":"1"},
    // {"name":"adjustApprove","number":"4"},
    // {"name":"resignApprove","number":"0"},
    // {"name":"gooutApprove","number":"1"},
    // {"name":"renewContractApprove","number":"0"},
    // {"name":"positiveContractApprove","number":"0"},
    // {"name":"appealApprove","number":"0"},
    // {"name":"badgeApprove","number":"1"},
    // {"name":"borrowApprove","number":"1"},
    // {"name":"administrativeCommonApprove","number":"1"},
    // {"name":"officeEquipmentApprove","number":"1"},
    // {"name":"purchaseApprove","number":"1"},
    // {"name":"administrativeCommonApply","number":"0"},
    // {"name":"badgeApply","number":"0"},
    // {"name":"borrowApply","number":"0"},
    // {"name":"officeEquipmentApply","number":"0"},
    // {"name":"purchaseApply","number":"0"},
    // {"name":"appealApply","number":"0"},
    // {"name":"vacationApply","number":"0"},
    // {"name":"overtimeApply","number":"0"},
    // {"name":"evectionApply","number":"0"},
    // {"name":"resignApply","number":"0"},
    // {"name":"gooutApply","number":"0"},
    // {"name":"vacationCarbonCopy","number":"0"},
    // {"name":"overTimeCarbonCopy","number":"0"},
    // {"name":"fieldWorkCarbonCopy","number":"0"},
    // {"name":"evectionCarbonCopy","number":"0"},
    // {"name":"renewContractCarbonCopy","number":"0"},
    // {"name":"probationContractCarbonCopy","number":"0"},
    // {"name":"payAdjustmentCarbonCopy","number":"0"},
    // {"name":"administrativeCommonCarbonCopy","number":"0"},
    // {"name":"badgeCarbonCopy","number":"0"},
    // {"name":"borrowCarbonCopy","number":"0"},
    // {"name":"officeEquipmentCarbonCopy","number":"0"},
    // {"name":"purchaseCarbonCopy","number":"0"},
    public static final String reward = "reward";
    // {"name":"reward","number":"0"},
    // {"name":"pmsParticipant","number":"0"},
    // {"name":"pmsWatcher","number":"0"},
    // {"name":"pmsLeader","number":"0"},
    // {"name":"kpiSelfAppraisal","number":"0"},
    // {"name":"kpiAppraisal","number":"2"},
    // {"name":"kpiSelfAppraisalHistory","number":"0"},
    // {"name":"workingCircle","number":"0"},
    // {"name":"reimbursementCarbonCopy","number":"0"},
    // {"name":"reimbursementApply","number":"0"},
    // {"name":"reimbursementApprove","number":"1"},
    // {"name":"hireApprove","number":"0"},
    // {"name":"hireCarbonCopy","number":"0"},
    // {"name":"meeting","number":"0"},
    // {"name":"recruitApprove","number":"2"},
    // {"name":"recruitCarbonCopy","number":"0"},
    // {"name":"recruitApply","number":"0"},
    // {"name":"workReport","number":"0"},
    // {"name":"kpiApprove","number":"6"},
    // {"name":"kpiApply","number":"0"},
    // {"name":"trainApprove","number":"2"},
    // {"name":"trainApply","number":"2"},
    // {"name":"trainerApply","number":"0"},
    // {"name":"trainNotify","number":"0"},
    // {"name":"appSystemMessageNotification","number":"0"},
    // {"name":"outSignApply","number":"0"},
    // {"name":"outSignApprove","number":"3"},
    // {"name":"outSignCarbonCopy","number":"0"},
    // {"name":"trainExamRequired","number":"0"},
    // {"name":"trainExamElective","number":"0"},
    // {"name":"trainExamOther","number":"0"},
    // {"name":"crmDealApply","number":"0"},
    // {"name":"crmDealApprove","number":"0"},
    // {"name":"user_customization_apply","number":"0"},
    // {"name":"user_customization_approve","number":"0"}]}
    private Map<String, Integer> unReadCountMap = new HashMap<>();
    public void saveUnReadCount(Map<String, Integer> unReadCount){
        unReadCountMap.putAll(unReadCount);
        setChanged();
        Set<Map.Entry<String, Integer>> entries = unReadCountMap.entrySet();
        for (Map.Entry<String, Integer> entry:entries){
            UnreadItemEntity unreadItemEntity = new UnreadItemEntity();
            unreadItemEntity.setName(entry.getKey());
            unreadItemEntity.setNumber(entry.getValue());
            notifyObservers(unreadItemEntity);
        }

        //首页
        UnreadItemEntity homeUnRead = new UnreadItemEntity();
        homeUnRead.setName(home);
        Integer myMessageCount = unReadCountMap.get(myMessage);
        Integer company_imCount = unReadCountMap.get(company_im);
        Integer rewardCount = unReadCountMap.get(reward);
        homeUnRead.setNumber(myMessageCount+company_imCount+rewardCount);
        notifyObservers(homeUnRead);
    }
    public void saveUnReadCount(String name,Integer value){
        unReadCountMap.put(name,value);
        UnreadItemEntity unreadItemEntity = new UnreadItemEntity();
        unreadItemEntity.setName(name);
        unreadItemEntity.setNumber(value);
        setChanged();
        notifyObservers(unreadItemEntity);
    }

    public static String getUnreadNameByItemId(String itemId) {
        switch (itemId){
            case HomeRouter.my_message:
                return myMessage;
            case HomeRouter.company_im:
                return company_im;
            case HomeRouter.reward_rank:
                return reward;

            default:
                return "";
        }
    }
}
