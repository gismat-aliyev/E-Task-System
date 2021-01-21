package az.com.etask.repository.sql;

public class TaskSQL {

    public static final String ADD_USER = "insert into users(user_id, username, password, email, full_name, status, role_id, company_id) " +
            "values (default,:username,:password,:email,:fullName,default,default,:companyId)";

    public static final String CREATE_COMPANY = "insert into company(company_id, company_name, company_email, company_phone, company_address) \n" +
            "VALUES(default,:companyName,:companyEmail,:companyPhone,:companyAddress)";

    public static final String GET_USER_BY_LOGIN_AND_PASSWORD = "select * from users where username=:username and password=:password and role_id = 1 and status=1";

    public static final String GET_USER_BY_ID = "select * from users where user_id=:userId ";

    public static final String GET_USER_BY_USERNAME = "select u.*,r.role_name as role_name,r.role_id as role_id from users u inner join role r on u.role_id = r.role_id where u.username=:username";

    public static final String ADD_TASK_TO_USER = "insert into task_to_user(ttu_id,task_id, user_id)\n" +
            "values (default,:taskId,:userId)";

    public static final String GET_TASK_BY_ID = "select* from tasks where task_id=:taskId and status = 1";

    public static final String CREATE_TASK = "insert into tasks(task_id, title, description, deadline, status, company_id) " +
            "values (default,:title,:description,:deadline,1,:companyId)";

    public static final String GET_TASK_LIST_BY_COMPANY_ID = "select u.user_id as user_id,u.username as username,u.email as email,u.full_name as full_name, t1.*\n" +
            "from users u inner join (\n" +
            "    select ttu_id,ttu.user_id as user_id ,t.title as title,t.task_id as task_id,\n" +
            "       t.description as description, t.deadline as deadline\n" +
            "from task_to_user ttu inner join tasks t on ttu.task_id = t.task_id) t1 on u.user_id = t1.user_id where u.company_id = :companyId";

    public static final String GET_USER_BY_ID_AND_COMPANY_ID = GET_USER_BY_ID + " and company_id=:companyId";

    public static final String ADD_ADMIN = "insert into users(user_id, username, password, email, full_name, status, role_id, company_id) " +
            "values (default,:username,:password,:email,:fullName,default,1,:companyId)";
}
