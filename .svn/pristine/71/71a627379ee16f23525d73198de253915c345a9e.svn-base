package com.gzf.util;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Date;
import java.util.Properties;

/**
 * Created by huangxiongbiao on 2017/12/5.
 */
public class GeneratorManager {
    private static final String USER = "root";
    private static final String PASS = "123";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    // 数据库连接120.55.117.108
    private static final String URL = "jdbc:mysql://localhost/zz_portal" + "?user=" + USER + "&password=" + PASS
            + "&useUnicode=true&characterEncoding=UTF-8";
    private static final String SAVE_CATALOG = "src/main/java";
    //  实体包名
    private static final String SAVE_PACKAGE = "com.gzf.persist.pojo";
    // mapper包名
    private static final String MAPPER_PACKAGE = "com.gzf.persist.mapper";
    // service包名
    private static final String SERVICE_PACKAGE = "com.gzf.service";
    // service实现包名
    private static final String SERVICEIML_PACKAGE = "com.gzf.service.iml";
    // controller包名
    private static final String CONTROLLER_PACKAGE = "com.gzf.web";
    private static final String REQUESTMAPPING = "";

    // 列注解和table注解的包路径
    private static final String COLUMN_PATH =
            "import com.lxy.persistence.mybatis.definition.annotation.Column;\n";
    private static final String TABLE_PATH =
            "import com.lxy.persistence.mybatis.definition.annotation.Table;\n";
    private static final String BASEMAP_PATH =
            "import com.gzf.persist.mapper.common.BaseMapper;\n";
    private static final String BASEEntity_PATH =
            "import com.gzf.persist.pojo.common.BaseEntity;\n";
    private static final String BASESERVICE_PATH =
            "import com.gzf.service.common.BaseService;\n";
    private static final String BASESERVICEIML_PATH =
            "import com.gzf.service.common.iml.BaseServiceIml;\n";

    private static Connection getConnection = null;
    private static DatabaseMetaData dbmd = null;

    public static void main(String[] args) {
        // TODO Auto-generated method stub
//        GeneratorManager.generateBaseEntity();
        GeneratorManager.generateEntity();
        GeneratorManager.generateMapper();
//        GeneratorManager.generateBaseService();
        GeneratorManager.generateService();
        GeneratorManager.generateServiceIml();
//        GeneratorManager.generatecontroller();
    }

    /**
     * 生成基类
     */
    public static void generateBaseEntity() {
        //创建类文件
        if (getEntitySavePath() == null) return;
        try {
            String classFilePath = getEntitySavePath() + "BaseEntity.java";
            //文件输出流
            FileWriter fw = new FileWriter(classFilePath);
            PrintWriter pw = new PrintWriter(fw);
            //先写package import文本
            StringBuffer fileBuffer = new StringBuffer();
            if (SAVE_PACKAGE != null && !SAVE_PACKAGE.equals(""))
                fileBuffer.append("package " + SAVE_PACKAGE + ";\r\n\n");
            fileBuffer.append("import java.util.Date;"+"\r\n");
            fileBuffer.append(COLUMN_PATH);

            //类实体文本
            StringBuffer contentBuffer = new StringBuffer();
            //类名称
            contentBuffer.append("\r\n\r\npublic class BaseEntity {\n\n");
            //属性文本
            StringBuffer attrBuffer = new StringBuffer();
            attrBuffer.append("\t@Column\r\n" +
                    "\tprivate Date ctime;\n\n");
            attrBuffer.append("\t@Column\r\n" +
                    "\tprivate Date mtime;\n\n");
            attrBuffer.append("\t@Column\r\n" +
                    "\tprivate String editState;\n\n");
            //方法文本
            StringBuffer methodBuffer = new StringBuffer("\r\n");
            methodBuffer.append("\tpublic Date getCtime() {\n\t\treturn ctime;\n\t}\n\n" +
                    "\tpublic void setCtime(Date ctime) {\n\t\tthis.ctime = ctime;\n\t}\n\n" +
                    "\tpublic Date getMtime() {\n\t\treturn mtime;\n\t}\n\n" +
                    "\tpublic void setMtime(Date mtime) {\n\t\tthis.mtime = mtime;\n\t}\n\n" +
                    "\tpublic String getEditState() {\n\t\treturn editState;\n\t}\n\n" +
                    "\tpublic void setEditState(String editState) {\n\t\tthis.editState = editState;\n\t}\n\n");

            contentBuffer.append(attrBuffer);
            contentBuffer.append(methodBuffer);
            contentBuffer.append("}\n");

            fileBuffer.append(contentBuffer);
            pw.println(fileBuffer.toString());

            pw.flush();
            pw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static {
        getConnection = getConnections();
        try {
            dbmd = getConnection.getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成实体类
     */
    public static void generateEntity() {
        try {
            ResultSet resultSet = dbmd.getTables(null, "%", "%", new String[]{"TABLE"});
            //迭代所有的表
            while (resultSet.next()) {
                //表名
                String tableName = resultSet.getString("TABLE_NAME");
                String tableRemake = resultSet.getString("REMARKS");
                //字段信息集合
                ResultSet colrs = dbmd.getColumns(null, "%", tableName, "%");

                //创建类文件
                if (getEntitySavePath() == null) break;
                String classFilePath = getEntitySavePath() + initialCap(tableName) + ".java";
                //文件输出流
                FileWriter fw = new FileWriter(classFilePath);
                PrintWriter pw = new PrintWriter(fw);
                //先写package import文本
                StringBuffer fileBuffer = new StringBuffer();
                if (SAVE_PACKAGE != null && !SAVE_PACKAGE.equals(""))
                    fileBuffer.append("package " + SAVE_PACKAGE + ";\r\n\n");
                fileBuffer.append("import java.io.Serializable;"+"\r\n");
                fileBuffer.append(BASEEntity_PATH);
                fileBuffer.append(COLUMN_PATH);
                fileBuffer.append(TABLE_PATH);
                //类实体文本
                StringBuffer contentBuffer = new StringBuffer();
                //类名称
                contentBuffer.append("\r\n");
                contentBuffer.append("/**\r\n");
                contentBuffer.append(" *\tCreated by admin on "+new Date()+"\r\n");
                contentBuffer.append(" *\t" + tableRemake + "\r\n");
                contentBuffer.append(" */\r\n");
                contentBuffer.append("@Table(\""+tableName+"\")\npublic class " + initialCap(tableName) +" extends BaseEntity implements Serializable"+ "{\r\n");
                //属性文本
                StringBuffer attrBuffer = new StringBuffer();
                //方法文本
                StringBuffer methodBuffer = new StringBuffer("\r\n");

                String primaryKey = null;
                ResultSet pkRSet = dbmd.getPrimaryKeys(null, null, tableName);
                while( pkRSet.next() ) {
                    primaryKey = (String) pkRSet.getObject(4);
                }

                //写入属性 setter和getter方法
                while (colrs.next()) {
                    String columnName = colrs.getString("COLUMN_NAME");
                    if (columnName.equals("ctime")||columnName.equals("mtime")||columnName.equals("editState")) continue;
                    if (columnName.equals(primaryKey)) {
                        addField(attrBuffer, colrs, true);
                    } else {
                        addField(attrBuffer, colrs, false);
                    }
                    if (sqlType2JavaType(colrs.getString("TYPE_NAME")).equals("Date")) {
                        fileBuffer.append("import java.util.Date;\r\n");
                    }
                    addMethod(methodBuffer, colrs);
                }

                contentBuffer.append(attrBuffer);
                contentBuffer.append(methodBuffer);
                contentBuffer.append("}\r\n");

                fileBuffer.append(contentBuffer);

                pw.println(fileBuffer.toString());

                pw.flush();
                pw.close();

            }
        } catch (SQLException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * 生成mapper
     */
    public static void generateMapper() {
        ResultSet resultSet = null;
        try {
            resultSet = dbmd.getTables(null, "%", "%", new String[]{"TABLE"});
            //迭代所有的表
            while (resultSet.next()) {
                //表名
                String tableName = resultSet.getString("TABLE_NAME");
                String tableRemake = resultSet.getString("REMARKS");
                //创建mapper文件
                if (getMapperSavePath() == null) break;
                String classFilePath = getMapperSavePath() + initialCap(tableName) + "Mapper.java";
                //文件输出流
                FileWriter fw = new FileWriter(classFilePath);
                PrintWriter pw = new PrintWriter(fw);
                //先写package import文本
                StringBuffer fileBuffer = new StringBuffer();
                if (MAPPER_PACKAGE != null && !MAPPER_PACKAGE.equals(""))
                    fileBuffer.append("package " + MAPPER_PACKAGE + ";\r\n\n");
                fileBuffer.append("import " + SAVE_PACKAGE + "." + initialCap(tableName)+";\n");
                fileBuffer.append(BASEMAP_PATH);
                fileBuffer.append("\r\n");
                fileBuffer.append("/**\r\n");
                fileBuffer.append(" *\tCreated by admin on "+new Date()+"\r\n");
                fileBuffer.append(" *\t" + tableName +" "+ tableRemake + "\r\n");
                fileBuffer.append(" */\r\n");
                fileBuffer.append("public interface " + initialCap(tableName) + "Mapper extends BaseMapper<" + initialCap(tableName) + ">{\n\n"+"}\n");

                pw.println(fileBuffer.toString());

                pw.flush();
                pw.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 生成baseservice
     */
    public static void generateBaseService() {
        //创建类文件
        if (getEntitySavePath() == null) return;
        try {
            String classFilePath = getServiceSavePath() + "BaseService.java";
            //文件输出流
            FileWriter fw = new FileWriter(classFilePath);
            PrintWriter pw = new PrintWriter(fw);
            //先写package import文本
            StringBuffer fileBuffer = new StringBuffer();
            if (SAVE_PACKAGE != null && !SAVE_PACKAGE.equals(""))
                fileBuffer.append("package " + SAVE_PACKAGE + ";\r\n\n");

            //类实体文本
            StringBuffer contentBuffer = new StringBuffer();
            //类名称
            contentBuffer.append("\r\n\r\npublic class BaseService {\n\n");
            //属性文本
            StringBuffer attrBuffer = new StringBuffer();
            attrBuffer.append("\t@Column\r\n" +
                    "\tprivate Date ctime;\n\n");
            attrBuffer.append("\t@Column\r\n" +
                    "\tprivate Date mtime;\n\n");
            attrBuffer.append("\t@Column\r\n" +
                    "\tprivate String editState;\n\n");
            //方法文本
            StringBuffer methodBuffer = new StringBuffer("\r\n");
            methodBuffer.append("\tpublic Date getCtime() {\n\t\treturn ctime;\n\t}\n\n" +
                    "\tpublic void setCtime(Date ctime) {\n\t\tthis.ctime = ctime;\n\t}\n\n" +
                    "\tpublic Date getMtime() {\n\t\treturn mtime;\n\t}\n\n" +
                    "\tpublic void setMtime(Date mtime) {\n\t\tthis.mtime = mtime;\n\t}\n\n" +
                    "\tpublic String getEditState() {\n\t\treturn editState;\n\t}\n\n" +
                    "\tpublic void setEditState(String editState) {\n\t\tthis.editState = editState;\n\t}\n\n");

            contentBuffer.append(attrBuffer);
            contentBuffer.append(methodBuffer);
            contentBuffer.append("}\n");

            fileBuffer.append(contentBuffer);
            pw.println(fileBuffer.toString());

            pw.flush();
            pw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成service
     */
    public static void generateService() {
        ResultSet resultSet = null;
        try {
            resultSet = dbmd.getTables(null, "%", "%", new String[]{"TABLE"});
            //迭代所有的表
            while (resultSet.next()) {
                //表名
                String tableName = resultSet.getString("TABLE_NAME");
                String tableRemake = resultSet.getString("REMARKS");
                //创建mapper文件
                if (getServiceSavePath() == null) break;
                String classFilePath = getServiceSavePath() + initialCap(tableName) + "Service.java";
                //文件输出流
                FileWriter fw = new FileWriter(classFilePath);
                PrintWriter pw = new PrintWriter(fw);
                //先写package import文本
                StringBuffer fileBuffer = new StringBuffer();
                if (SERVICE_PACKAGE != null && !SERVICE_PACKAGE.equals(""))
                    fileBuffer.append("package " + SERVICE_PACKAGE + ";\r\n\n");
                fileBuffer.append("import org.springframework.web.bind.annotation.RestController;"+"\r\n");
                fileBuffer.append("import org.springframework.web.bind.annotation.RequestMapping;"+"\r\n");
                fileBuffer.append(BASESERVICE_PATH);
                fileBuffer.append("import " + SAVE_PACKAGE + "." +initialCap(tableName)+ ";"+"\r\n");
                fileBuffer.append("\r\n");
                fileBuffer.append("/**\r\n");
                fileBuffer.append(" *\tCreated by admin on "+new Date()+"\r\n");
                fileBuffer.append(" *\t" + tableName +" "+ tableRemake + "\r\n");
                fileBuffer.append(" */\r\n");
                fileBuffer.append("@RestController\r\n");
                fileBuffer.append("@RequestMapping(\""+REQUESTMAPPING+"/"+subPrefix(tableName)+"\")\r\n");
                fileBuffer.append("public interface " + initialCap(tableName) + "Service extends BaseService<"+initialCap(tableName)+">{\n\n}\n");

                pw.println(fileBuffer.toString());

                pw.flush();
                pw.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成serviceIml
     */
    public static void generateServiceIml() {
        ResultSet resultSet = null;
        try {
            resultSet = dbmd.getTables(null, "%", "%", new String[]{"TABLE"});
            //迭代所有的表
            while (resultSet.next()) {
                //表名
                String tableName = resultSet.getString("TABLE_NAME");
                String tableRemake = resultSet.getString("REMARKS");
                //创建mapper文件
                if (getServiceImlSavePath() == null) break;
                String classFilePath = getServiceImlSavePath() + initialCap(tableName) + "ServiceIml.java";
                //文件输出流
                FileWriter fw = new FileWriter(classFilePath);
                PrintWriter pw = new PrintWriter(fw);
                //先写package import文本
                StringBuffer fileBuffer = new StringBuffer();
                if (SERVICEIML_PACKAGE != null && !SERVICEIML_PACKAGE.equals(""))
                    fileBuffer.append("package " + SERVICEIML_PACKAGE + ";\r\n\n");
                fileBuffer.append("import " + SERVICE_PACKAGE + "." + initialCap(tableName)+"Service;\n");
                fileBuffer.append("import org.springframework.stereotype.Service;"+"\r\n");
                fileBuffer.append("import org.springframework.transaction.annotation.Transactional;"+"\r\n");
                fileBuffer.append("import org.springframework.beans.factory.annotation.Autowired;"+"\r\n");
                fileBuffer.append("import " + SAVE_PACKAGE + "." +initialCap(tableName)+ ";"+"\r\n");
                fileBuffer.append("import " + MAPPER_PACKAGE + "." +initialCap(tableName)+ "Mapper;"+"\r\n");
                fileBuffer.append(BASESERVICEIML_PATH);
                fileBuffer.append(BASEMAP_PATH);
                fileBuffer.append("\r\n");
                fileBuffer.append("/**\r\n");
                fileBuffer.append(" *\tCreated by admin on "+new Date()+"\r\n");
                fileBuffer.append(" *\t" + tableName +" "+ tableRemake + "\r\n");
                fileBuffer.append(" */\r\n");
                fileBuffer.append("@Service\r\n");
                fileBuffer.append("@Transactional\r\n");
                fileBuffer.append("public class " + initialCap(tableName) + "ServiceIml extends BaseServiceIml<"+initialCap(tableName)+"> implements "+ initialCap(tableName) +"Service {\n");

                fileBuffer.append("\n\t@Autowired\n\t" +initialCap(tableName)+"Mapper "+subPrefix(tableName)+"Mapper;\n\n");
                fileBuffer.append("\t@Override\n" +
                        "\tprotected BaseMapper getMapper() {\n" +
                        "\t\treturn "+subPrefix(tableName)+"Mapper;\n" +
                        "\t}");

                fileBuffer.append("\n}\n");
                pw.println(fileBuffer.toString());

                pw.flush();
                pw.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成controller
     */
    public static void generatecontroller() {
        ResultSet resultSet = null;
        try {
            resultSet = dbmd.getTables(null, "%", "%", new String[]{"TABLE"});
            //迭代所有的表
            while (resultSet.next()) {
                //表名
                String tableName = resultSet.getString("TABLE_NAME");
                String tableRemake = resultSet.getString("REMARKS");
                //创建mapper文件
                if (getControllerSavePath() == null) break;
                String classFilePath = getControllerSavePath() + initialCap(tableName) + "Controller.java";
                //文件输出流
                FileWriter fw = new FileWriter(classFilePath);
                PrintWriter pw = new PrintWriter(fw);
                //先写package import文本
                StringBuffer fileBuffer = new StringBuffer();
                if (CONTROLLER_PACKAGE != null && !CONTROLLER_PACKAGE.equals(""))
                    fileBuffer.append("package " + CONTROLLER_PACKAGE + ";\r\n\n");
                fileBuffer.append("import org.springframework.web.bind.annotation.RestController;"+"\r\n");
                fileBuffer.append("import org.springframework.web.bind.annotation.RequestMapping;"+"\r\n");
                fileBuffer.append("\r\n");
                fileBuffer.append("/**\r\n");
                fileBuffer.append(" *\tCreated by admin on "+new Date()+"\r\n");
                fileBuffer.append(" *\t" + tableName +" "+ tableRemake + "\r\n");
                fileBuffer.append(" */\r\n");
                fileBuffer.append("@RestController\r\n");
                fileBuffer.append("@RequestMapping(\""+REQUESTMAPPING+"/"+tableName+"\")\r\n");
                fileBuffer.append("public class " + initialCap(tableName) + "Controller {\n\n}\n");

                pw.println(fileBuffer.toString());

                pw.flush();
                pw.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 建立数据库连接
     *
     * @return
     */
    public static Connection getConnections() {
        try {
            Properties props =new Properties();
            props.setProperty("remarks", "true"); //设置可以获取remarks信息
            props.setProperty("useInformationSchema", "true");//设置可以获取tables remarks信息
            Class.forName(DRIVER);
            getConnection = DriverManager.getConnection(URL, props);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getConnection;
    }

    public static String getEntitySavePath() {
        return getSavePath(SAVE_PACKAGE);
    }

    public static String getMapperSavePath() {
        return getSavePath(MAPPER_PACKAGE);
    }

    public static String getServiceSavePath() {
        return getSavePath(SERVICE_PACKAGE);
    }

    public static String getServiceImlSavePath() {
        return getSavePath(SERVICEIML_PACKAGE);
    }

    public static String getControllerSavePath() {
        return getSavePath(CONTROLLER_PACKAGE);
    }

    /**
     * 创建文件夹
     * @param savePath
     * @return
     */
    public static String getSavePath(String savePath) {
        String path = null;
        if (savePath == null || savePath.equals("")) {
            //获取当前用户桌面路径
            FileSystemView fsv = FileSystemView.getFileSystemView();
            path = fsv.getHomeDirectory().toString();
        } else {
            path = SAVE_CATALOG;
        }

        if (!savePath.equals("")) path = path + "/" + savePath.replace(".", "/") + "/";
        System.out.println(path);

        //创建目录
        File dir = new File(path);
        if (!dir.exists()) {
            if (dir.mkdirs()) {
                System.out.println("创建目录成功！");
            } else {
                System.out.println("创建目录失败！");
                return null;
            }
        }

        return path;
    }

    /**
     * 生成成员属性
     *
     * @param contentBuffer
     * @param resultSet
     * @return
     * @throws SQLException
     */
    private static StringBuffer addField(StringBuffer contentBuffer, ResultSet resultSet, boolean isPrimaryKey) throws SQLException {
        if (contentBuffer == null || resultSet == null) return contentBuffer;
        //先写注释
        contentBuffer.append("\r\n");
        contentBuffer.append("\t/**\r\n");
        contentBuffer.append("\t *\t" + resultSet.getString("REMARKS") + "\r\n");
        contentBuffer.append("\t */\r\n");
        if (isPrimaryKey) {
            contentBuffer.append("\t@Column(type = Column.Type.ID)\r\n");
        }else {
            contentBuffer.append("\t@Column\r\n");
        }
        //写属性
        contentBuffer.append("\tprivate " + sqlType2JavaType(resultSet.getString("TYPE_NAME")) + " "
                + columnName2AttrName(resultSet.getString("COLUMN_NAME")) + ";\r\n");

        return contentBuffer;
    }

    /**
     * 生成属性setter getter
     *
     * @param contentBuffer
     * @param resultSet
     * @return
     * @throws SQLException
     */
    private static StringBuffer addMethod(StringBuffer contentBuffer, ResultSet resultSet) throws SQLException {
        if (contentBuffer == null || resultSet == null) return contentBuffer;
        //属性名
        String attrName = columnName2AttrName(resultSet.getString("COLUMN_NAME"));
        //属性名首字母大写
        String capMethodName = initialCapCommon(attrName);
        //属性类型
        String attrType = sqlType2JavaType(resultSet.getString("TYPE_NAME"));

        contentBuffer.append("\tpublic void set" + capMethodName + "(" + attrType + " " +
                attrName + "){\r\n");
        contentBuffer.append("\t\tthis." + attrName + "=" + attrName + ";\r\n");
        contentBuffer.append("\t}\r\n\r\n");
        contentBuffer.append("\tpublic " + attrType + " get" + capMethodName + "(){\r\n");
        contentBuffer.append("\t\treturn " + attrName + ";\r\n");
        contentBuffer.append("\t}\r\n\r\n");
        return contentBuffer;
    }

    /**
     * 截掉数据库前缀
     * @return
     */
    private static String subPrefix(String tableName) {
        return tableName;//.substring(4, tableName.length());
    }

    /**
     * 功能：将输入字符串的首字母改成大写
     *
     * @param str
     * @return
     */
    private static String initialCap(String str) {
        str = subPrefix(str);
        String className = columnName2AttrName(str);
        char[] ch = className.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }

        return new String(ch);
    }

    /**
     * 功能：将输入字符串的首字母改成大写
     *
     * @param str
     * @return
     */
    private static String initialCapCommon(String str) {
        String className = columnName2AttrName(str);
        char[] ch = className.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }

        return new String(ch);
    }

    /**
     * 将数据表字段转化成类的属性名称  （下划线后一字符小写转大写，去掉下划线）
     *
     * @param columnName
     * @return
     */
    private static String columnName2AttrName(String columnName) {
        if (columnName == null) return columnName;
        if (columnName.contains("_")) {
            char[] ch = columnName.toCharArray();
            for (int index = 0; index < ch.length; index++) {
                if (ch[index] == '_' && index > 0 && index < ch.length - 1) {
                    if (ch[index + 1] >= 'a' && ch[index + 1] <= 'z') {
                        ch[index + 1] = (char) (ch[index + 1] - 32);
                    }
                }
            }

            return new String(ch).replace("_", "");
        }
        return columnName;
    }

    /**
     * 功能：获得列的数据类型
     *
     * @param sqlType
     * @return
     */
    private static String sqlType2JavaType(String sqlType) {

        if (sqlType.equalsIgnoreCase("bit")) {
            return "Boolean";
        } else if (sqlType.equalsIgnoreCase("tinyint")) {
            return "Integer";
        } else if (sqlType.equalsIgnoreCase("smallint")) {
            return "Short";
        } else if (sqlType.equalsIgnoreCase("int")) {
            return "Integer";
        } else if (sqlType.equalsIgnoreCase("bigint")) {
            return "Long";
        } else if (sqlType.equalsIgnoreCase("float")) {
            return "Float";
        } else if (sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric")
                || sqlType.equalsIgnoreCase("real") || sqlType.equalsIgnoreCase("money")
                || sqlType.equalsIgnoreCase("smallmoney")|| sqlType.equalsIgnoreCase("double")) {
            return "Double";
        } else if (sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char")
                || sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar")
                || sqlType.equalsIgnoreCase("text")) {
            return "String";
        } else if (sqlType.equalsIgnoreCase("datetime") || sqlType.equalsIgnoreCase("timestamp")) {
            return "Date";
        } else if (sqlType.equalsIgnoreCase("image")) {
            return "Blod";
        }

        return null;
    }

}
