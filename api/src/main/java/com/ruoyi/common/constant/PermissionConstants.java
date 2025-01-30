package com.ruoyi.common.constant;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 权限常量
 *
 * @author : sinvon
 * @since :  2025/1/30 下午8:57
 */
public class PermissionConstants {

    /**
     * 广告
     */
    public static final String ADMIN_ADVERTISEMENT_LIST = "admin:advertisement:list";
    public static final String ADMIN_ADVERTISEMENT_EXPORT = "admin:advertisement:export";
    public static final String ADMIN_ADVERTISEMENT_QUERY = "admin:advertisement:query";
    public static final String ADMIN_ADVERTISEMENT_ADD = "admin:advertisement:add";
    public static final String ADMIN_ADVERTISEMENT_EDIT = "admin:advertisement:edit";
    public static final String ADMIN_ADVERTISEMENT_REMOVE = "admin:advertisement:remove";

    /**
     * 广告日志
     */
    public static final String ADMIN_ADVERTISEMENT_LOG_LIST = "admin:advertisementLog:list";
    public static final String ADMIN_ADVERTISEMENT_LOG_EXPORT = "admin:advertisementLog:export";
    public static final String ADMIN_ADVERTISEMENT_LOG_QUERY = "admin:advertisementLog:query";
    public static final String ADMIN_ADVERTISEMENT_LOG_ADD = "admin:advertisementLog:add";
    public static final String ADMIN_ADVERTISEMENT_LOG_EDIT = "admin:advertisementLog:edit";
    public static final String ADMIN_ADVERTISEMENT_LOG_REMOVE = "admin:advertisementLog:remove";

    /**
     * 地区
     */
    public static final String ADMIN_AREA_LIST = "admin:area:list";
    public static final String ADMIN_AREA_EXPORT = "admin:area:export";
    public static final String ADMIN_AREA_QUERY = "admin:area:query";
    public static final String ADMIN_AREA_ADD = "admin:area:add";
    public static final String ADMIN_AREA_EDIT = "admin:area:edit";
    public static final String ADMIN_AREA_REMOVE = "admin:area:remove";

    /**
     * 书签文章
     */
    public static final String ADMIN_BOOKMARK_ARTICLE_LIST = "admin:bookmarkArticle:list";
    public static final String ADMIN_BOOKMARK_ARTICLE_EXPORT = "admin:bookmarkArticle:export";
    public static final String ADMIN_BOOKMARK_ARTICLE_QUERY = "admin:bookmarkArticle:query";
    public static final String ADMIN_BOOKMARK_ARTICLE_ADD = "admin:bookmarkArticle:add";
    public static final String ADMIN_BOOKMARK_ARTICLE_EDIT = "admin:bookmarkArticle:edit";
    public static final String ADMIN_BOOKMARK_ARTICLE_REMOVE = "admin:bookmarkArticle:remove";

    /**
     * 书签分类
     */
    public static final String ADMIN_BOOKMARK_CATEGORY_LIST = "admin:bookmarkCategory:list";
    public static final String ADMIN_BOOKMARK_CATEGORY_EXPORT = "admin:bookmarkCategory:export";
    public static final String ADMIN_BOOKMARK_CATEGORY_QUERY = "admin:bookmarkCategory:query";
    public static final String ADMIN_BOOKMARK_CATEGORY_ADD = "admin:bookmarkCategory:add";
    public static final String ADMIN_BOOKMARK_CATEGORY_EDIT = "admin:bookmarkCategory:edit";
    public static final String ADMIN_BOOKMARK_CATEGORY_REMOVE = "admin:bookmarkCategory:remove";

    /**
     * 书签
     */
    public static final String ADMIN_BOOKMARK_LIST = "admin:bookmark:list";
    public static final String ADMIN_BOOKMARK_EXPORT = "admin:bookmark:export";
    public static final String ADMIN_BOOKMARK_QUERY = "admin:bookmark:query";
    public static final String ADMIN_BOOKMARK_ADD = "admin:bookmark:add";
    public static final String ADMIN_BOOKMARK_EDIT = "admin:bookmark:edit";
    public static final String ADMIN_BOOKMARK_REMOVE = "admin:bookmark:remove";

    /**
     * 书签日志
     */
    public static final String ADMIN_BOOKMARK_LOG_LIST = "admin:bookmarkLog:list";
    public static final String ADMIN_BOOKMARK_LOG_EXPORT = "admin:bookmarkLog:export";
    public static final String ADMIN_BOOKMARK_LOG_QUERY = "admin:bookmarkLog:query";
    public static final String ADMIN_BOOKMARK_LOG_ADD = "admin:bookmarkLog:add";
    public static final String ADMIN_BOOKMARK_LOG_EDIT = "admin:bookmarkLog:edit";
    public static final String ADMIN_BOOKMARK_LOG_REMOVE = "admin:bookmarkLog:remove";

    /**
     * 书签标签
     */
    public static final String ADMIN_BOOKMARK_TAG_LIST = "admin:bookmarkTag:list";
    public static final String ADMIN_BOOKMARK_TAG_EXPORT = "admin:bookmarkTag:export";
    public static final String ADMIN_BOOKMARK_TAG_QUERY = "admin:bookmarkTag:query";
    public static final String ADMIN_BOOKMARK_TAG_ADD = "admin:bookmarkTag:add";
    public static final String ADMIN_BOOKMARK_TAG_EDIT = "admin:bookmarkTag:edit";
    public static final String ADMIN_BOOKMARK_TAG_REMOVE = "admin:bookmarkTag:remove";

    /**
     * 浏览历史
     */
    public static final String ADMIN_BROWSE_HISTORY_LIST = "admin:browseHistory:list";
    public static final String ADMIN_BROWSE_HISTORY_EXPORT = "admin:browseHistory:export";
    public static final String ADMIN_BROWSE_HISTORY_QUERY = "admin:browseHistory:query";
    public static final String ADMIN_BROWSE_HISTORY_ADD = "admin:browseHistory:add";
    public static final String ADMIN_BROWSE_HISTORY_EDIT = "admin:browseHistory:edit";
    public static final String ADMIN_BROWSE_HISTORY_REMOVE = "admin:browseHistory:remove";

    /**
     * 客服
     */
    public static final String ADMIN_CUSTOMER_SERVICE_LIST = "admin:customerService:list";
    public static final String ADMIN_CUSTOMER_SERVICE_EXPORT = "admin:customerService:export";
    public static final String ADMIN_CUSTOMER_SERVICE_QUERY = "admin:customerService:query";
    public static final String ADMIN_CUSTOMER_SERVICE_ADD = "admin:customerService:add";
    public static final String ADMIN_CUSTOMER_SERVICE_EDIT = "admin:customerService:edit";
    public static final String ADMIN_CUSTOMER_SERVICE_REMOVE = "admin:customerService:remove";

    /**
     * 反馈
     */
    public static final String ADMIN_FEEDBACK_LIST = "admin:feedback:list";
    public static final String ADMIN_FEEDBACK_EXPORT = "admin:feedback:export";
    public static final String ADMIN_FEEDBACK_QUERY = "admin:feedback:query";
    public static final String ADMIN_FEEDBACK_ADD = "admin:feedback:add";
    public static final String ADMIN_FEEDBACK_EDIT = "admin:feedback:edit";
    public static final String ADMIN_FEEDBACK_REMOVE = "admin:feedback:remove";

    /**
     * 反馈日志
     */
    public static final String ADMIN_FEEDBACK_LOG_LIST = "admin:feedbackLog:list";
    public static final String ADMIN_FEEDBACK_LOG_EXPORT = "admin:feedbackLog:export";
    public static final String ADMIN_FEEDBACK_LOG_QUERY = "admin:feedbackLog:query";
    public static final String ADMIN_FEEDBACK_LOG_ADD = "admin:feedbackLog:add";
    public static final String ADMIN_FEEDBACK_LOG_EDIT = "admin:feedbackLog:edit";
    public static final String ADMIN_FEEDBACK_LOG_REMOVE = "admin:feedbackLog:remove";

    /**
     * 反馈类型
     */
    public static final String ADMIN_FEEDBACK_TYPE_LIST = "admin:feedbackType:list";
    public static final String ADMIN_FEEDBACK_TYPE_EXPORT = "admin:feedbackType:export";
    public static final String ADMIN_FEEDBACK_TYPE_QUERY = "admin:feedbackType:query";
    public static final String ADMIN_FEEDBACK_TYPE_ADD = "admin:feedbackType:add";
    public static final String ADMIN_FEEDBACK_TYPE_EDIT = "admin:feedbackType:edit";
    public static final String ADMIN_FEEDBACK_TYPE_REMOVE = "admin:feedbackType:remove";

    /**
     * 友情链接
     */
    public static final String ADMIN_FRIENDLINK_LIST = "admin:friendlink:list";
    public static final String ADMIN_FRIENDLINK_EXPORT = "admin:friendlink:export";
    public static final String ADMIN_FRIENDLINK_QUERY = "admin:friendlink:query";
    public static final String ADMIN_FRIENDLINK_ADD = "admin:friendlink:add";
    public static final String ADMIN_FRIENDLINK_EDIT = "admin:friendlink:edit";
    public static final String ADMIN_FRIENDLINK_REMOVE = "admin:friendlink:remove";

    /**
     * 友情链接日志
     */
    public static final String ADMIN_FRIENDLINK_LOG_LIST = "admin:friendlinkLog:list";
    public static final String ADMIN_FRIENDLINK_LOG_EXPORT = "admin:friendlinkLog:export";
    public static final String ADMIN_FRIENDLINK_LOG_QUERY = "admin:friendlinkLog:query";
    public static final String ADMIN_FRIENDLINK_LOG_ADD = "admin:friendlinkLog:add";
    public static final String ADMIN_FRIENDLINK_LOG_EDIT = "admin:friendlinkLog:edit";
    public static final String ADMIN_FRIENDLINK_LOG_REMOVE = "admin:friendlinkLog:remove";

    /**
     * 邀请码
     */
    public static final String ADMIN_INVITE_CODE_LIST = "admin:inviteCode:list";
    public static final String ADMIN_INVITE_CODE_EXPORT = "admin:inviteCode:export";
    public static final String ADMIN_INVITE_CODE_QUERY = "admin:inviteCode:query";
    public static final String ADMIN_INVITE_CODE_ADD = "admin:inviteCode:add";
    public static final String ADMIN_INVITE_CODE_EDIT = "admin:inviteCode:edit";
    public static final String ADMIN_INVITE_CODE_REMOVE = "admin:inviteCode:remove";

    /**
     * 邀请码日志
     */
    public static final String ADMIN_INVITE_CODE_LOG_LIST = "admin:inviteCodeLog:list";
    public static final String ADMIN_INVITE_CODE_LOG_EXPORT = "admin:inviteCodeLog:export";
    public static final String ADMIN_INVITE_CODE_LOG_QUERY = "admin:inviteCodeLog:query";
    public static final String ADMIN_INVITE_CODE_LOG_ADD = "admin:inviteCodeLog:add";
    public static final String ADMIN_INVITE_CODE_LOG_EDIT = "admin:inviteCodeLog:edit";
    public static final String ADMIN_INVITE_CODE_LOG_REMOVE = "admin:inviteCodeLog:remove";

    /**
     * 留言
     */
    public static final String ADMIN_MESSAGE_LIST = "admin:message:list";
    public static final String ADMIN_MESSAGE_EXPORT = "admin:message:export";
    public static final String ADMIN_MESSAGE_QUERY = "admin:message:query";
    public static final String ADMIN_MESSAGE_ADD = "admin:message:add";
    public static final String ADMIN_MESSAGE_EDIT = "admin:message:edit";
    public static final String ADMIN_MESSAGE_REMOVE = "admin:message:remove";

    /**
     * 留言日志
     */
    public static final String ADMIN_MESSAGE_LOG_LIST = "admin:messageLog:list";
    public static final String ADMIN_MESSAGE_LOG_EXPORT = "admin:messageLog:export";
    public static final String ADMIN_MESSAGE_LOG_QUERY = "admin:messageLog:query";
    public static final String ADMIN_MESSAGE_LOG_ADD = "admin:messageLog:add";
    public static final String ADMIN_MESSAGE_LOG_EDIT = "admin:messageLog:edit";
    public static final String ADMIN_MESSAGE_LOG_REMOVE = "admin:messageLog:remove";

    /**
     * 通知
     */
    public static final String ADMIN_NOTICE_LIST = "admin:notice:list";
    public static final String ADMIN_NOTICE_EXPORT = "admin:notice:export";
    public static final String ADMIN_NOTICE_QUERY = "admin:notice:query";
    public static final String ADMIN_NOTICE_ADD = "admin:notice:add";
    public static final String ADMIN_NOTICE_EDIT = "admin:notice:edit";
    public static final String ADMIN_NOTICE_REMOVE = "admin:notice:remove";

    /**
     * 通知关联用户
     */
    public static final String ADMIN_NOTICE_USER_LIST = "admin:noticeUser:list";
    public static final String ADMIN_NOTICE_USER_EXPORT = "admin:noticeUser:export";
    public static final String ADMIN_NOTICE_USER_QUERY = "admin:noticeUser:query";
    public static final String ADMIN_NOTICE_USER_ADD = "admin:noticeUser:add";
    public static final String ADMIN_NOTICE_USER_EDIT = "admin:noticeUser:edit";
    public static final String ADMIN_NOTICE_USER_REMOVE = "admin:noticeUser:remove";

    /**
     * 支付类型
     */
    public static final String ADMIN_PAYMENT_TYPE_LIST = "admin:paymentType:list";
    public static final String ADMIN_PAYMENT_TYPE_EXPORT = "admin:paymentType:export";
    public static final String ADMIN_PAYMENT_TYPE_QUERY = "admin:paymentType:query";
    public static final String ADMIN_PAYMENT_TYPE_ADD = "admin:paymentType:add";
    public static final String ADMIN_PAYMENT_TYPE_EDIT = "admin:paymentType:edit";
    public static final String ADMIN_PAYMENT_TYPE_REMOVE = "admin:paymentType:remove";

    /**
     * 积分
     */
    public static final String ADMIN_POINT_LIST = "admin:point:list";
    public static final String ADMIN_POINT_EXPORT = "admin:point:export";
    public static final String ADMIN_POINT_QUERY = "admin:point:query";
    public static final String ADMIN_POINT_ADD = "admin:point:add";
    public static final String ADMIN_POINT_EDIT = "admin:point:edit";
    public static final String ADMIN_POINT_REMOVE = "admin:point:remove";

    /**
     * 积分日志
     */
    public static final String ADMIN_POINT_LOG_LIST = "admin:pointLog:list";
    public static final String ADMIN_POINT_LOG_EXPORT = "admin:pointLog:export";
    public static final String ADMIN_POINT_LOG_QUERY = "admin:pointLog:query";
    public static final String ADMIN_POINT_LOG_ADD = "admin:pointLog:add";
    public static final String ADMIN_POINT_LOG_EDIT = "admin:pointLog:edit";
    public static final String ADMIN_POINT_LOG_REMOVE = "admin:pointLog:remove";

    /**
     * 搜索日志
     */
    public static final String ADMIN_SEARCH_LOG_LIST = "admin:searchLog:list";
    public static final String ADMIN_SEARCH_LOG_EXPORT = "admin:searchLog:export";
    public static final String ADMIN_SEARCH_LOG_QUERY = "admin:searchLog:query";
    public static final String ADMIN_SEARCH_LOG_ADD = "admin:searchLog:add";
    public static final String ADMIN_SEARCH_LOG_EDIT = "admin:searchLog:edit";
    public static final String ADMIN_SEARCH_LOG_REMOVE = "admin:searchLog:remove";

    /**
     * 设置
     */
    public static final String ADMIN_SETTING_LIST = "admin:setting:list";
    public static final String ADMIN_SETTING_EXPORT = "admin:setting:export";
    public static final String ADMIN_SETTING_QUERY = "admin:setting:query";
    public static final String ADMIN_SETTING_ADD = "admin:setting:add";
    public static final String ADMIN_SETTING_EDIT = "admin:setting:edit";
    public static final String ADMIN_SETTING_REMOVE = "admin:setting:remove";

    /**
     * 设置日志
     */
    public static final String ADMIN_SETTING_LOG_LIST = "admin:settingLog:list";
    public static final String ADMIN_SETTING_LOG_EXPORT = "admin:settingLog:export";
    public static final String ADMIN_SETTING_LOG_QUERY = "admin:settingLog:query";
    public static final String ADMIN_SETTING_LOG_ADD = "admin:settingLog:add";
    public static final String ADMIN_SETTING_LOG_EDIT = "admin:settingLog:edit";
    public static final String ADMIN_SETTING_LOG_REMOVE = "admin:settingLog:remove";

    /**
     * 用户
     */
    public static final String ADMIN_USER_LIST = "admin:user:list";
    public static final String ADMIN_USER_EXPORT = "admin:user:export";
    public static final String ADMIN_USER_QUERY = "admin:user:query";
    public static final String ADMIN_USER_ADD = "admin:user:add";
    public static final String ADMIN_USER_EDIT = "admin:user:edit";
    public static final String ADMIN_USER_REMOVE = "admin:user:remove";

    /**
     * 用户登录日志
     */
    public static final String ADMIN_USER_LOGIN_LOG_LIST = "admin:userLoginLog:list";
    public static final String ADMIN_USER_LOGIN_LOG_EXPORT = "admin:userLoginLog:export";
    public static final String ADMIN_USER_LOGIN_LOG_QUERY = "admin:userLoginLog:query";
    public static final String ADMIN_USER_LOGIN_LOG_ADD = "admin:userLoginLog:add";
    public static final String ADMIN_USER_LOGIN_LOG_EDIT = "admin:userLoginLog:edit";
    public static final String ADMIN_USER_LOGIN_LOG_REMOVE = "admin:userLoginLog:remove";

    /**
     * 用户等级
     */
    public static final String ADMIN_USER_GRADE_LIST = "admin:userGrade:list";
    public static final String ADMIN_USER_GRADE_EXPORT = "admin:userGrade:export";
    public static final String ADMIN_USER_GRADE_QUERY = "admin:userGrade:query";
    public static final String ADMIN_USER_GRADE_ADD = "admin:userGrade:add";
    public static final String ADMIN_USER_GRADE_EDIT = "admin:userGrade:edit";
    public static final String ADMIN_USER_GRADE_REMOVE = "admin:userGrade:remove";

    /**
     * VIP
     */
    public static final String ADMIN_VIP_LIST = "admin:vip:list";
    public static final String ADMIN_VIP_EXPORT = "admin:vip:export";
    public static final String ADMIN_VIP_QUERY = "admin:vip:query";
    public static final String ADMIN_VIP_ADD = "admin:vip:add";
    public static final String ADMIN_VIP_EDIT = "admin:vip:edit";
    public static final String ADMIN_VIP_REMOVE = "admin:vip:remove";

    /**
     * VIP日志
     */
    public static final String ADMIN_VIP_LOG_LIST = "admin:vipLog:list";
    public static final String ADMIN_VIP_LOG_EXPORT = "admin:vipLog:export";
    public static final String ADMIN_VIP_LOG_QUERY = "admin:vipLog:query";
    public static final String ADMIN_VIP_LOG_ADD = "admin:vipLog:add";
    public static final String ADMIN_VIP_LOG_EDIT = "admin:vipLog:edit";
    public static final String ADMIN_VIP_LOG_REMOVE = "admin:vipLog:remove";

    /**
     * VIP类型
     */
    public static final String ADMIN_VIP_TYPE_LIST = "admin:vipType:list";
    public static final String ADMIN_VIP_TYPE_EXPORT = "admin:vipType:export";
    public static final String ADMIN_VIP_TYPE_QUERY = "admin:vipType:query";
    public static final String ADMIN_VIP_TYPE_ADD = "admin:vipType:add";
    public static final String ADMIN_VIP_TYPE_EDIT = "admin:vipType:edit";
    public static final String ADMIN_VIP_TYPE_REMOVE = "admin:vipType:remove";

    /**
     * 网站信息
     */
    public static final String ADMIN_WEBSITE_INFO_LIST = "admin:websiteInfo:list";
    public static final String ADMIN_WEBSITE_INFO_EXPORT = "admin:websiteInfo:export";
    public static final String ADMIN_WEBSITE_INFO_QUERY = "admin:websiteInfo:query";
    public static final String ADMIN_WEBSITE_INFO_ADD = "admin:websiteInfo:add";
    public static final String ADMIN_WEBSITE_INFO_EDIT = "admin:websiteInfo:edit";
    public static final String ADMIN_WEBSITE_INFO_REMOVE = "admin:websiteInfo:remove";


    private enum Type {
        AgentGrades("代理等级"),
        Agents("代理商"),
        AgentOrders("代理商订单"),

        //        个人
        Self("自己"),
        ;
        private final String description;

        Type(String description) {
            this.description = description;
        }

        public String get() {
            return this.name() + "【" + description + "】";
        }
    }


    public static Map<String, String> getAllAuthorities() {

        if (authorities == null) {
            synchronized (PermissionConstants.class) {
                if (authorities == null) {
                    authorities = Arrays.stream(PermissionConstants.class.getFields())
                            .filter(field -> field.getType() == String.class)
                            .collect(Collectors.toMap(f -> {
                                try {
                                    return (String) f.get(null);
                                } catch (IllegalAccessException e) {
                                    throw new RuntimeException(e);
                                }
                            }, f -> {
                                Note annotation = f.getAnnotation(Note.class);
                                return annotation != null ? annotation.value() : "";
                            }));
                }
            }
        }
        return authorities;
    }

    private static volatile Map<String, String> authorities;

    public static List<ActionAssembly> getActionAssemblies() {
        if (actionAssemblies.isEmpty()) {
            synchronized (PermissionConstants.class) {
                if (actionAssemblies.isEmpty()) {
                    createAllAction();
                }
            }
        }
        return actionAssemblies;
    }

    public static String queryAuthority(String fieldName) {
        try {
            Field field = PermissionConstants.class.getField(fieldName);
            if (field.get(null) instanceof String s) {
                return s;
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static String queryNoteType(String fieldName) {
        try {
            Field field = PermissionConstants.class.getField(fieldName);
            Note note = field.getAnnotation(Note.class);
            if (note != null) {
                return note.type().name();
            }
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private static void createAllAction() {
        Arrays.stream(PermissionConstants.class.getFields())
                .filter(field -> field.getType() == String.class)
                .filter(field -> field.getAnnotation(Note.class) != null)
                .collect(Collectors.groupingBy(field -> field.getAnnotation(Note.class).type()))
                .forEach(PermissionConstants::assemble);

    }

    private static void assemble(Type type, List<Field> fields) {
        String name = type.get();
        List<Action> actionList = fields.stream().map(f -> {
            String value = f.getAnnotation(Note.class).value();
            String fieldName = f.getName();
            return new Action(fieldName + "【" + value + "】", value, fieldName);
        }).toList();
        actionAssemblies.add(new ActionAssembly(name, actionList));
    }

    private static final List<ActionAssembly> actionAssemblies = new ArrayList<>();


    public record ActionAssembly(String name, List<Action> actions) {
    }


    record Action(String name, String description, String actionName) {
    }

    /**
     * @author 陈国庆
     * @since 2022/12/8 16:01
     */
    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    private @interface Note {
        String value();

        Type type();
    }
}