package com.zhaolw.zoo.boot.controller.mail;

import com.zhaolw.zoo.boot.common.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * excel导出并发送DEMO
 *
 * @author zhaoliwei
 * @date 2019/9/18 08:40
 **/
@Slf4j
public class ExportSendMail {

    private static final String SUCCESS_CODE = "200";


    public static void main(String[] args) throws IOException {
        ExportSendMail exportSendMail = new ExportSendMail();

        //获取excle流
        InputStream is = null;
        try {
            is = exportSendMail.export();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //发送服务器
        String host = "smtp.163.com";
        //发送账号
        String account = "mro_test@163.com";
        //发送密码
        String password = "mro223344";
        //开始发送
        String message = ExportSendMail.sendMail(is, "邮件标题", "邮件正文", "附件文件名.xlsx", "390181291@qq.com", host, account, password);

        if (is != null) {
            is.close();
        }
        log.info("邮件发送结果：" + message);
    }


    private InputStream export() {
        //表的标题名称
        String sheetName = "库存预警";
//表头集合
        List<String> tableHead = new ArrayList<>();
        tableHead.add("商品编码");
        tableHead.add("上限");
        tableHead.add("下限");

        //表格内数据内容，此处为一个双层集合数组，外层数组代表的是总的表格内容，数组长度为行数（不包括标题和表头），
        //第二层数组类似于行，数组内每个元素对应一个单元格，因此二层数组长度应与表头数组长度一致，不然会出现单元格缺失
        List<List<Object>> tableBody = new ArrayList<>();
        List<Object> tableRow1 = new ArrayList<>();
        tableRow1.add("TY10002");
        tableRow1.add(12);
        tableRow1.add(3);

        List<Object> tableRow2 = new ArrayList<>();
        tableRow2.add("BG01223");
        tableRow2.add(20);
        tableRow2.add(5);

        tableBody.add(tableRow1);
        tableBody.add(tableRow2);
        try {
            return exportXls(sheetName, tableHead, tableBody);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 生成表格并返回结果流
     *
     * @param sheetName 表名
     * @param tableHead 表头
     * @param tableBody 表格数据（双层集合数组）
     * @return 生成的表格以流的形式返回
     * @throws Exception
     */
    private InputStream exportXls(String sheetName, List<String> tableHead, List<List<Object>> tableBody)
            throws Exception {

        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建一张excel表
        HSSFSheet sheet = workbook.createSheet(sheetName);
        //   ---------------- 表标题样式 -------------------
        HSSFFont headfont = workbook.createFont();
        headfont.setFontName("宋体");
        // 设置字体大小
        headfont.setFontHeightInPoints((short) 18);
        //粗体显示
        headfont.setBold(true);
        //设置单元格样式
        HSSFCellStyle headstyle = workbook.createCellStyle();
        //设置文字样式
        headstyle.setFont(headfont);
        // 单元格内容左右居中
        // 设置单元格上下居中
        headstyle.setAlignment(HorizontalAlignment.CENTER);
        headstyle.setVerticalAlignment(VerticalAlignment.CENTER);
        // 设置标题固定
        headstyle.setLocked(true);

//   ---------------  表头样式 -----------------
        HSSFFont font = workbook.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 12);
        HSSFCellStyle style = workbook.createCellStyle();
        //设置表头背景色
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
        style.setFillBackgroundColor(IndexedColors.GREY_25_PERCENT.index);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //单元格边框属性设置
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setLocked(true);

//   -------------- 普通单元格样式（中文） ---------------
        HSSFFont font2 = workbook.createFont();
        font2.setFontName("宋体");
        font2.setFontHeightInPoints((short) 12);
        font2.setBold(true);
        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setBorderBottom(BorderStyle.THIN);
        style2.setBorderLeft(BorderStyle.THIN);
        style2.setBorderRight(BorderStyle.THIN);
        style2.setBorderTop(BorderStyle.THIN);
        style2.setFont(font2);
        // 设置单元格内容自动换行
        style2.setWrapText(true);
        style2.setAlignment(HorizontalAlignment.CENTER);
        style2.setVerticalAlignment(VerticalAlignment.CENTER);

        //设置默认行高
        sheet.setDefaultRowHeight((short) 360);
        // 第一行，添加表的标题
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, tableHead.size() - 1));
        HSSFRow row = sheet.createRow(0);
        row.setHeight((short) 0x349);
        HSSFCell cell = row.createCell(0);
        cell.setCellStyle(headstyle);
        CellUtil.setCellValue(cell, sheetName);

        //第二行，添加表头
        row = sheet.createRow(1);
        row.setHeight((short) 0x180);
        for (int j = 0; j < tableHead.size(); j++) {
            //设置每列的宽度 （自定义列宽） 4200为每列最小宽度
            int dataLength = tableHead.get(j).getBytes().length * 2 * 232;
            sheet.setColumnWidth((short) j, dataLength < 4200 ? 4200 : dataLength);
            //在行内创建一个单元格
            cell = row.createCell(j);
            //单元格设置自定样式 style（上边有定义）
            cell.setCellStyle(style);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            //将数据填充进表格内 cell：创建的单元格    tableHead.get(j):数据
            CellUtil.setCellValue(cell, tableHead.get(j));
        }

        // 设置列值-每行数据的内容
        for (int i = 0; i < tableBody.size(); i++) {
            //标题、表头字段共占了2行，所以在填充数据的时候要加2，也就是数据要从第3行开始填充
            row = sheet.createRow(i + 2);
            for (int j = 0; j < tableBody.get(i).size(); j++) {
                cell = row.createCell(j);
                cell.setCellStyle(style2);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                CellUtil.setCellValue(cell, tableBody.get(i).get(j));
            }
        }

        //以下代码需要修改为下载到某个路径
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        workbook.write(bos);
        return new ByteArrayInputStream(bos.toByteArray());
    }


    /**
     * 发送邮件
     *
     * @param is         表格输出流
     * @param fileName   发送的文件名称 (例：xxxx统计表)
     * @param reUserMail 收件人邮箱（例：12345678@qq.com）
     */
    private static String sendMail(InputStream is, String title, String text, String fileName, String reUserMail, String host, String account, String password) {
        log.info("[ {}开始发送邮件标题：{} 正文：{}，文件：{}，收件人：{}]", account, title, text, fileName, reUserMail);
        if (StringUtil.isBlank(title)) {
            title = "您有邮件";
        }
        if (StringUtil.isBlank(text)) {
            text = "您有邮件";
        }
        if (StringUtil.isBlank(fileName)) {
            fileName = "您有邮件";
        }
        if (is == null) {
            return "邮件附件不能为空";
        }
        if (StringUtil.isBlank(reUserMail)) {
            return "收件人不能为空";
        }
        if (StringUtil.isBlank(host) || StringUtil.isBlank(account) || StringUtil.isBlank(password)) {
            return "邮箱服务器参数不能为空";
        }
        Transport transport = null;
        try {
            System.setProperty("mail.mime.splitlongparameters", "false");
            Properties props = new Properties();
            // 设置发送邮件的邮件服务器的属性（这里使用网易的smtp服务器）
            props.put("mail.smtp.host", host);
            // 需要经过授权，也就是有户名和密码的校验，这样才能通过验证（一定要有这一条）
            props.put("mail.smtp.auth", "true");
            // 用刚刚设置好的props对象构建一个session
            Session session = Session.getDefaultInstance(props);
            // 有了这句便可以在发送邮件的过程中在console处显示过程信息，供调试使
            // 用（你可以在控制台（console)上看到发送邮件的过程）
            session.setDebug(false);
            // 用session为参数定义消息对象
            MimeMessage message = new MimeMessage(session);
            // 加载发件人地址
            message.setFrom(new InternetAddress(account));
            // 加载收件人地址
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(reUserMail));
            // 加载标题
            message.setSubject(title);
            // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
            Multipart multipart = new MimeMultipart();

            // 设置邮件的文本内容
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setHeader("Content-Type", "text/html; charset=UTF-8");
            contentPart.setText(text);
            multipart.addBodyPart(contentPart);
            // 添加附件
            BodyPart messageBodyPart = new MimeBodyPart();
            DataSource source = new ByteArrayDataSource(is, "application/msexcel");
            // 添加附件的内容
            messageBodyPart.setDataHandler(new DataHandler(source));
            // 添加附件的标题
            // 这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码
            messageBodyPart.setFileName(MimeUtility.encodeText(fileName));
            multipart.addBodyPart(messageBodyPart);

            // 将multipart对象放到message中
            message.setContent(multipart);
            message.addHeader("charset", "UTF-8");
            // 保存邮件
            message.saveChanges();
            // 发送邮件
            transport = session.getTransport("smtp");
            // 连接服务器的邮箱
            transport.connect(host, account, password);
            // 把邮件发送出去
            transport.sendMessage(message, message.getAllRecipients());
            log.info("sendMail success");
            return SUCCESS_CODE;
        } catch (Exception e) {
            String message = MessageFormat.format("sendMail error:{0}；{1}", e.getClass().getName(), e.getMessage() != null ? e.getMessage() : "");
            log.error(message);
            return message;
        } finally {
            try {
                if (transport != null) {
                    transport.close();
                }
            } catch (Exception e) {
                transport = null;
                log.error("mail close the failure", e);
            }
        }

    }

}
