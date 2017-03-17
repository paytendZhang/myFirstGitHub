package com.gd.rps.biz.dem

import com.gd.magic.RootEntity
import com.gd.magic.anno.Column
import com.gd.rps.biz.dem.Card
import com.gd.rps.biz.dem.CardGrade
import com.gd.rps.biz.dem.CardType
import com.gd.rps.biz.dem.constant.CardUserStatus
import com.gd.rps.biz.dem.constant.CardUserType
import com.gd.rps.biz.dom.CardRequestOrder
import com.gd.rps.biz.owm.CompanyCustomer
import com.gd.rps.biz.owm.Customer
import com.gd.rps.biz.owm.UserDept
import com.gd.rps.biz.tdm.Operator
import com.gd.rps.biz.tdm.Organization
import com.gd.rps.biz.tdm.constant.TreeType
import com.gd.rps.biz.tdm.constant.AuditStatus
import com.gd.rps.biz.sym.constant.SysCardType
import com.gd.rps.biz.tdm.EuropeRate;

//import com.gd.domain.constant.PropertyType
//import com.gd.card.constant.InvoiceType
/**
 * Describe the owner information of a issued card.
 * @version V0.1
 * @copyright(c) Copyright FXGS Corporation 2009.
 * @author dingzhiming
 */
class CardUser extends com.gd.magic.RootEntity implements Cloneable{
	
	
    /**
     * 姓
     * first name
     */
	@Column(size=96)
	String firstName	
	
	
    /**
     * 名
     * last name
     */
	@Column(size=96)
	String name	
	/**
	 * 证件号
	 * credentials id(id card, e.g.).
	 */
	@Column(size=32)
	String idno 					
	/**
	 * 帐号编号
	 * Unique id for card user.
	 */
	@Column(size=12, unique=true)
	String uniqueId 					
	/**
	 * 当前卡号 
	 * Current card id
	 */
	@Column(index=true)
	long currentAsn
	/**
	 * 帐号类型
	 * Account type
	 */
	@Column(dict="carduser_type")
	int type 	
	/**
	 * 帐号的状态
	 * Account status
	 */
	@Column(dict="carduser_status")
	int status
	/**
	 * 帐号冻结原因
	 * reason of freeze card
	 */
	@Column(size=80)
	String freezeReason
	
	/**
	 * 用户当前的卡片申请单
	 * current card order
	 */
	CardRequestOrder cardRequestOrder
	/**
	 * 卡片等级 ? 有问题   改为 CardGrade  grade
	 * current device level
	 */
	//@Column(dict="customer_level")
	//int currentLevel
	CardGrade currentLevel
	/**
	 * 开户的组织机构
     * 现在写的是发卡机构
	 * Open account organization 
	 */
	Organization location
	/**
	 * 卡密码  - userPin起名有问题，卡密码叫offlinePin相对合理
	 * card password
	 */
	@Column(size=12,columnName="userPin")
	String cardPin
	
	
	/**
	 * 是否启用
	 * card password
	 */
	 boolean usePin
	
	
	/**
	 * 客户档案
	 * Related card owner
	 */
	Customer customer
	/**
	 * 单位档案
	 * companyCustomer
	 */
	CompanyCustomer companyCustomer 	
	/**
	 * 单位帐号 
	 * unit accounts 
	 */
	CardUser orgUser 					
	/**
	 * 是否单位主卡 
	 * whether or not nuit card
	 */
	boolean isMasterCard 				
	/**
	 * 额度值
	 *  limit for credit
	 */
	long creditLimit 					
	/**
	 * 备录
	 *  remark
	 */
	@Column(size=64)
	String notes 						
	/**
	 * 是否内部卡 
	 * whether or not internal card
	 */
	boolean internal					
	/**
	 * 不记名卡升级成记名卡，记录不记名卡初始金额
	 *  Anonymous card upgrade into registered card and initial amount of innominate card is to be recorded.
	 */
	long anonymousBalance				
	/**
	 * 司机卡所属部门
	 * the drivers' deptment
	 */
	UserDept dept 						
	/**
	 * 单位指定的卡片类型，每个单位只有一种卡片类型
	 * Units specified card types and each unit is only one card types.
	 */
	CardType cardType					
	/**
	 * 建立时间
	 * recordTime
	 */
	@Column(columnName='recordDate')
	Date recordTime 					
	/**
	 * 建立的网点
	 * buildFileStation
	 */
	Organization buildFileStation 		
	/**
	 * 建立操作员
	 * operator
	 */
	@Column(columnName='operator_id')
	Operator oper 						
	/**
	 * 当前卡
	 * currentCard
	 */
	Card currentCard 					
	/**
	 * 每天消费次数
	 * timeLimit one day
	 */
	int timeLimit 						
	/**
	 * 每天消费金额
	 * amountLimit one day
	 */
	long amountLimit 					
	/**
	 * 消费频率限制(多少天能消费一次)
	 * oilFrequencyLimit(how many days can add oil)
	 */
	int frequency	
	
	/**
	 * 每次消费金额限制
	 * Amount limit one time
	 */
	long quantityNumber 
	
	 /**
	   * 联机支付PIN
	  */
	 @Column(size=16)
	 String onLinePin
	 
	 /**
	   * APP联机登录密码
	  */
	 @Column(size=16)
	 String onLinePassword
	   
	 /**
	  * 联机支付密码PIN尝试次数
	  */
	 int pinTryTimes = 0;
	
	 /**
	  * APP登录PIN尝试次数
	  */
	 int pwdTryTimes = 0;
	
	/** EMV限制信息 **/
	@Column(isTransient=true)
	int upperLimit;
	
	@Column(isTransient=true)
	int perTxLimit;
	
	@Column(isTransient=true)
	int offlineUpper;
	
	@Column(isTransient=true)
	int offlineLower;
	
	@Column(isTransient=true)
	int resetThreshold;
	

	/*易生芯片卡*/
	@Column(isTransient=true)
	int chipUpperLimit; //电子现金最高限额 
	
	@Column(isTransient=true)
	int chipPerTxLimit; //电子现金单笔最高限额
	
	@Column(isTransient=true)
	int chipResetThreshold; //充值阈值
	
	@Column(isTransient=true) 
	int chipLoadAmountValue //电子现金圈存协议值
	/**
	 * 网上交易密码
	 */
	@Column(size=64)
	String internetPin
	
	/**
	 * 邮箱:可登录用
	 */
	@Column(size=64,unique=true)
	String email
	/**
	 * 电话号码:可登录用
	 */
	@Column(size=64,unique=true)
	String telNum
	
	@Column(size=2)
	String gender;
	
	/**
	 * 国家代码
	 */
	@Column(size=16)
	String countryCode
	
	/**
	 * 国家名称
	 */
	@Column(size=16)
	String countryName
	
	/**
    * 一级代理
    */
   Organization topOrgLevel;
   
   /**
    * 二级代理
    */
   Organization secondOrgLevel;
	
   /**
    * 用户是否第一次登陆
    */
   @Column(columnName="isFirstLogin")
   int isFirstLogin;
   
   /**
    * 是否是代理商(保存代理商上级ID)
    */
   Organization isAgent;
	
   /**
    * 经度
    */
   String lng;
   
   /**
    * 纬度
    */
   String lat;
   
   /**
    * 代理商所在地
    */
   String address;
   
   /**
    * 分控审核状态(实际的状态)
    * 1:待审核；2：审核通过；3：审核拒绝
    */
   long fengkongStatus;
   
   /**
    * 申请想成为的级别（小商户还是大商户）,默认值为0（基本没用）
    * 1:KYB0;2:KYB1
    */
   long applyGrade;
   
   /**
    * 时区：例如：中国是东八区，数据库存储20(所有的时区在运算时都减去12)
    */
   int timezone; 
   
   /**
    * 用户级别：1：普通用户；2：上传了凭证的用户（改变状态之前，风控审核是通过状态）；3：小商户（改变状态之前，风控审核是通过状态）；4：大商户（改变状态之前，风控审核是通过状态）；
    * 400：二级代理商；300：一级代理商
    */
   int userLevel;
   
   /**
    * 出生日期
    */
   String birthday;
   
	def setUserLimit(){
		if(!SysCardType.isPboc2()) return;
		
		EmvCardTemplate t = currentCard?.templet;
		if(t){
			upperLimit = t.upperLimit;
			perTxLimit = t.perTxLimit;
			offlineUpper = t.offlineUpper;
			offlineLower = t.offlineLower;
			resetThreshold = t.resetThreshold;

			chipUpperLimit = t.chipUpperLimit;
			chipPerTxLimit = t.chipPerTxLimit;
			chipResetThreshold = t.chipResetThreshold;
			chipLoadAmountValue = t.chipLoadAmountValue;

		
		}
	}

	/**
	 * 校验联机PIN
	 */
	public def verifyOnlineKey(String pin) {
		if(pinTryTimes >= 6){	//暂时使用6
			assert false: X("password_lock");
		}
	    assert pin:X("PREUPDATE_OPERATOR_PASSWORD");
	    if(organizationService.encrypt(pin).equals(this.onLinePin)){
	    	if(pinTryTimes!=0)
	    		{
	    		pinTryTimes = 0;
		    	ejbService.merge(this);
	    		}
	    	
	    }else{
	    	pinTryTimes++;
	    	setUserLimit();
	    	ejbService.tx.merge(this);
	    	assert false:X("pin_try_times",[6-pinTryTimes]);
	    }
	    return true
	  }
	/**
	 * 校验APP登录密码
	 */
	public def verifyOnlinePwd(String pin) {
		if(pwdTryTimes >= 6){	//暂时使用6
			assert false: X("password_lock");
		}
	    assert pin:X("PREUPDATE_OPERATOR_PASSWORD");
	    if(organizationService.encrypt(pin).equals(this.onLinePassword)){
	    	if(pwdTryTimes!=0)
	    		{
	    		pwdTryTimes = 0;
		    	ejbService.merge(this);
	    		}
	    	
	    }else{
	    	pwdTryTimes++;
	    	setUserLimit();
	    	ejbService.tx.merge(this);
	    	assert false:X("pin_try_times",[6-pwdTryTimes]);
	    }
	    return true
	  }
	
	public void preUpdate(boolean isNew) {
		//assert currentLevel>=0&&currentLevel<256 :'wrong currentLevel,must between 0 and 100'
		//assert contractType>=0&&contractType<100000000 :'wrong contractType,must between 0 and 100000000'
		if (isNew) {
			//assert onLinePin: "onLinePin is null";
			
			Organization cOrg = loginService.currentOrg()
			Organization cOrgParent = organizationService.getParentOrg(cOrg,TreeType.TREE_BASIC)
			recordTime = formatService.getHourOfTime(new Date())
			if (!buildFileStation) {
				buildFileStation = cOrg
			}

         // if (!location){
//				assert cOrg.orgLevel == OrgLevel.STATION || cOrg.orgLevel == OrgLevel.CITY : X(dictService.getText(OrgLevel.CATEGORY,cOrg.orgLevel)+"无权创建客户帐户")


		   if(!location)
		   {
	           if (currentCard) {
	               	 location = currentCard.targetOrg      
	               	 currentLevel = currentCard.type.userLevel
	             } else if(currentAsn) {
	            	 Card c = Card.loadByAsn(currentAsn)
	            	 location = c.type.org
	            	 currentLevel = c.type.userLevel
	             }else if(cardType){  //建单位账户是没有卡  只有卡类型
	            	 location = cardType.org;
                   	 currentLevel = cardType.userLevel;
               }
		   }else{
			   if (currentCard) {
	               currentLevel = currentCard.type.userLevel
	             } else if(currentAsn){
	               Card c = Card.loadByAsn(currentAsn)
	               currentLevel = c.type.userLevel
	             }else if(cardType){  //建单位账户是没有卡  只有卡类型
                   	 currentLevel = cardType.userLevel;
               }
		   }
		assert currentLevel:X("no card grade")
		assert location&&location.isIssuer:X("account no issuer")
 		 
		//检查限制信息是否符合Issuer的全局参数的设置
		if(SysCardType.isPboc1()){
			AbstractCardTemplate templet = new CardTemplate(amountLimit:amountLimit,timeLimit:timeLimit,quantityNumber:quantityNumber,frequency:frequency);
			cardMgntService.checkUDCardTemplate(templet,location);
		}

              //location = cOrg.orgLevel == OrgLevel.STATION ? cOrgParent : cOrg
		//	}
			if (!oper){
				oper = loginService.currentOperator()
			}
			if(!internal) internal=false
			
			if (status == 0)
				status = CardUserStatus.OK
				
			if (type==CardUserType.COMPANY){
					orgUser = this
				}
			
	
			
			/*if (!currentLevel){
				if (type == CardUserType.NAME)
					currentLevel = 1
				else if (type == CardUserType.DRIVER || type == CardUserType.COMPANY)
					currentLevel = 100
			}
			*/
/*			if (type == CardUserType.DRIVER){
				if(canLoadPoint == null) 
					canLoadPoint = false
			}else{
				canLoadPoint = true
			}
*/
			//if (type==CardUserType.DRIVER){
			//	invoiceType = orgUser.invoiceType
			//}
		}
		if (type==CardUserType.DRIVER){
			assert orgUser: X("CARDUSER_ORGUSER_WRONG")
			if (dept)
				assert dept.orgUser==orgUser: X("CARDUSER_DEPT_ORGUSER_WRONG")
			//assert !creditOrg : X("CARDUSER_CREDITORG_WRONG")					//credit
			//contractType = orgUser.contractType
			//contractExpiredDate = orgUser.contractExpiredDate
			companyCustomer = orgUser.companyCustomer
			cardType = orgUser.cardType
			assert orgUser.location.id==location.id : X("DRIVER_LOCATION_ERROR")
		}else{
//			if (type == CardUserType.CREDIT){								//credit
//				assert creditOrg : X("CARDUSER_CREDITORG_WRONG")
//			}
			assert type==CardUserType.COMPANY || !orgUser: X("CARDUSER_NOT_ORGUSER_WRONG")
			assert !dept: X("CARDUSER_NOT_DEPT_WRONG")
		}
		/*
		if (!isOilLimit){
			oilLimit = cardDataService.cantGoodsLimit("F",8)
		}else{
			if (!oilLimit)
				oilLimit = cardDataService.cantGoodsLimit("0",8)
		}
		if (!isMerLimit){
			merchandiseLimit = cardDataService.cantGoodsLimit("F",15)
		}else{
			if (!merchandiseLimit){//禁止购买商品，填15*8个0,分号分割;
				merchandiseLimit = cardDataService.cantGoodsLimit("0",15)
			}
		}*/
		
		//email 在数据库存储为小写
		if(email){
			email=email.toLowerCase()
		}
		if(countryCode){
			
			EuropeRate er = EuropeRate.loadByCountryCode(countryCode);
			println "er.timezone = " + er.timezone;
			timezone = er.timezone;
			
		}else{
			timezone = 13;
		
		}
			
		super.preUpdate(isNew)
	}
	
	public void postUpdate(boolean isNew){
		if(SysCardType.isPboc2() && isNew){
			AbstractCardTemplate templet = EmvCardTemplate.loadByCardUser(this);
			templet = cardMgntService.updataEmvCardTemplate(this,templet);
			cardMgntService.checkUDCardTemplate(templet,location);
			if(currentCard){
				currentCard.templet = templet;
				currentCard.merge();
			}
		}
		
	}
	
	public void preRemove() {
		if (type==CardUserType.COMPANY){
			assert !CardUser.loadByOrgUser(this): X("CARDUSER_PREREMOVE_WRONG")
			UserDept.removeByOrgUser(this)
		}
		//accountService.removeUserAccounts(this)
		super.preRemove()
	}

	public Object clone() {
		def obj = null;
		try {
			obj = super.clone();
			obj.id = null;
			obj.version = 0;
            obj.location=location;//批量添加司机卡时 要主账户的location
            obj.currentLevel=currentLevel;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return obj;
	}
}