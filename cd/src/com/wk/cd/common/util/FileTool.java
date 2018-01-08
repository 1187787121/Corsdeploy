/**
 * Title: FileTool.java
 * File Description: File������
 * @copyright 2014 
 * @company CORSWORK
 * @author lixl
 * @version 1.0
 * @date 12/1/2014
 */

package com.wk.cd.common.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;

import com.wk.cd.exc.FileNotExistException;
import com.wk.util.FileUtil;

/**
 * Class Description: File������
 * @author lixl
 */
public class FileTool {
	private static final String LINE_SPE = "/";
	private static final int MAX_BF_LEN = 1024 * 1024;

	/**
	 * ת���ļ�·�� "\\"ת��Ϊ"/"
	 * @author lixl (2014-12-1)
	 * @param pathFile
	 * @return String
	 */
	public static String filePathCvt(String pathFile) {
		Assert.assertNotEmpty(pathFile, "pathFile");
		return pathFile.replace("\\", "/");
	}

	/**
	 * ��鱾���ļ��Ƿ����
	 * @author lixl (2014-12-1)
	 * @param file
	 */
	public static void checkLocalFile(File file) {
		if (Assert.isEmpty(file) || !file.exists()) {
			throw new FileNotExistException().addScene("FILE", "�����ļ���" + file.getName());
		}
	}

	/**
	 * �����ļ�ȫ·�����ƻ�ȡ�ļ�·��
	 * @author lixl (2014-12-1)
	 * @param file
	 * @return String
	 */
	public static String getFilePath(String file) {
		Assert.assertNotEmpty(file, "file");
		String _file = filePathCvt(file);
		String path = ".";
		int index = _file.lastIndexOf(LINE_SPE);
		if (index > 0) {
			path = _file.substring(0, index + 1);
		}
		return path;
	}

	/**
	 * �����ļ�ȫ·�����ƻ�ȡ�ļ���
	 * @author lixl (2014-12-1)
	 * @param file
	 * @return String
	 */
	public static String getFileName(String file) {
		Assert.assertNotEmpty(file, "file");
		String _file = filePathCvt(file);
		int index = _file.lastIndexOf(LINE_SPE);
		if (index > 0) {
			return _file.substring(index + 1);
		} else {
			return _file;
		}
	}

	/**
	 * �����ļ�ȫ����ȡ�ļ�����
	 * @author lixl (2014-12-1)
	 * @param pathFile �ļ�ȫ·������
	 * @return File
	 */
	public static File getFile(String pathFile) {
		Assert.assertNotEmpty(pathFile, "pathFile");
		return new File(pathFile);
	}

	/**
	 * ���Ŀ¼�����ڴ���Ŀ¼
	 * @author lixl (2014-12-1)
	 * @param file
	 */
	public static void mkdir(File file) {
		Assert.assertNotEmpty(file, "file");
		String p = getFilePath(file.getPath());
		File f = getFile(p);
		if (!f.exists()) {
			f.mkdirs();
		}
	}

	/**
	 * �����ļ�·������·��
	 * @author lixl (2014-12-19)
	 * @param path ·��·��
	 */
	public static void mkdir(String path) {
		mkdir(getFile(path + "/inner$$$.tmp"));
	}

	/**
	 * �����ļ����ƴ����ļ�
	 * @return File
	 */
	public static File createFile(String pathFile) {
		return createFile(getFile(pathFile));
	}

	/**
	 * @param file �����ļ�
	 * @return File
	 */
	public static File createFile(File file) {
		Assert.assertNotEmpty(file, "file");
		mkdir(file);
		return FileUtil.createFile(file);
	}

	/**
	 * д���ļ�
	 * @param image
	 * @param file
	 */
	public static void writeFileImage(byte[] image, File file) {
		FileUtil.writeFileImage(image, file);
	}

	/**
	 * �����ļ���д���ļ�
	 * @param image
	 * @param pathFile
	 */
	public static void writeFileImage(byte[] image, String pathFile) {
		File file = createFile(pathFile);
		writeFileImage(image, file);
	}

	/**
	 * ��ȡ�ļ�����
	 * @author lixl (2014-12-9)
	 * @param file
	 * @return String
	 */
	public static String readFile(File file) {
		StringBuffer br = new StringBuffer();
		byte[] image = new byte[MAX_BF_LEN];
		int size = readFileImage(file, image);
		for (int i = 0; i < size; i++) {
			br.append(image[i]);
		}
		return br.toString();
	}

	/**
	 * ��ȡ�ļ�����
	 * @author lixl (2014-12-9)
	 * @param file
	 * @return byte[]
	 */
	public static int readFileImage(File file, byte[] image) {
		return FileUtil.readFileImage(file, image);
	}

	/**
	 * ����byte�����ݴ�С��Ӧ���ı�
	 * @param size
	 * @return
	 */
	public static String getFormatSize(long size) {
		DecimalFormat formater = new DecimalFormat("####.00");
		if (size < 1024) {
			return size + "bytes";
		} else if (size < 1024 * 1024) {
			float kbsize = size / 1024f;
			return formater.format(kbsize) + "KB";
		} else if (size < 1024 * 1024 * 1024) {
			float mbsize = size / 1024f / 1024f;
			return formater.format(mbsize) + "MB";
		} else if (size < 1024 * 1024 * 1024 * 1024) {
			float gbsize = size / 1024f / 1024f / 1024f;
			return formater.format(gbsize) + "GB";
		} else {
			return "size: error";
		}
	}

	
	/**
	 * ��ȡ�ļ� Description:
	 * 
	 * @param file
	 * @param pos
	 * @param size
	 * @return
	 * @throws IOException 
	 */
	public static byte[] getFileToByte(File file, long pos, int size) throws IOException {

		byte[] by = new byte[size]; // ָ������
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(fis);

		bis.skip(pos); // ָ��λ��
		bis.read(by);

		bis.close();
		fis.close();

		return by;

	}

	/**
	 * ���ļ�������������ļ�
	 * 
	 * @param content
	 * @param outputFile
	 * @return
	 * @throws IOException
	 */
	public static void writeFileFromBytes(byte[] content, long pos, String outputFile) throws IOException {
		 // ��һ����������ļ���������д��ʽ
        RandomAccessFile randomFile = new RandomAccessFile(outputFile, "rw");
        
        //��д�ļ�ָ���Ƶ��ļ�β��
        randomFile.seek(pos);
        randomFile.write(content);
        randomFile.close();
	}
	
	/**
     * ��ȡ�ļ���׺��
     * @param file
     * @return String
     */
    public static String getFileType(String file) {
        file = getFileName(file);
        int i = file.lastIndexOf('.');
        if (i >= 0) {
            return file.substring(i+1);
        } else {
            return file;
        }
    }
    
	/**
	 * ���ļ�������������ļ�
	 * 
	 * @param content
	 * @param outputFile
	 * @return
	 * @throws IOException
	 */
	public static void writeFileFromBytes(byte[] content, long pos, String file_path, String file_name) throws IOException {
		
		File file = new File(file_path);//��ȡ�ļ���·��
		if(!file.exists()){//�ж��ļ����Ƿ񴴽���û�д����򴴽����ļ���
			file.mkdirs();
        }
        
		String outputFile = file_path + file_name;
		
		 // ��һ����������ļ���������д��ʽ
        RandomAccessFile randomFile = new RandomAccessFile(outputFile, "rw");
        
        //��д�ļ�ָ���Ƶ��ļ�β��
        randomFile.seek(pos);
        randomFile.write(content);
        randomFile.close();
	}
}