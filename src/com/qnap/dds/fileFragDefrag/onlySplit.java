package com.qnap.dds.fileFragDefrag;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

public class onlySplit {

	public onlySplit() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		onlySplit ops= new onlySplit();
		//ops.join("/D:/JAR/Sptest/Big Hero 6.avi1.sp");
		String FilePath = "/D:/JAR/Sptest/Big Hero 6.avi1.sp";
		File filename = new File(FilePath.trim());
		if (filename.exists()) {
			FilePath = FilePath.substring(0, FilePath.length() - 4);
			filename = new File(FilePath);
			if (filename.exists()) {
				System.out.println("\"" + FilePath + "\" File Already Exist....");
			} else {
				fileSplitJoin spObj = new fileSplitJoin();
				ops.join(FilePath);
				ops = null;
			}
		} else {
			System.out.println(FilePath + filename);
			System.out.println("File Not Found....");
		}
		//ops.split("/D:/JAR/Sptest/Big Hero 6.avi", 128 * 1024 * 1024);
	}
	public void split(String FilePath, long splitlen) {
		long leninfile = 0, leng = 0,startTime,endTime;
		int count = 1, data;
		try {
			startTime = new Date().getTime();
			File filename = new File(FilePath);
			//RandomAccessFile infile = new RandomAccessFile(filename, "r");
			InputStream infile = new BufferedInputStream(new FileInputStream(filename));
			data = infile.read();
			while (data != -1) {
				filename = new File(FilePath + count + ".sp");
				//RandomAccessFile outfile = new RandomAccessFile(filename, "rw");
				OutputStream outfile = new BufferedOutputStream(new FileOutputStream(filename));
				while (data != -1 && leng < splitlen) {
					outfile.write(data);
					leng++;
					data = infile.read();
				}
				leninfile += leng;
				leng = 0;
				outfile.close();
				count++;
			}
			 endTime= new Date().getTime() - startTime;
			 System.out.println("\n Total time taken : " + endTime +" Mili seconds");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void join(String FilePath) {
		long leninfile = 0, leng = 0,startTime,endTime;
		int count = 1, data = 0;
		
		try {
			startTime = new Date().getTime();
			File filename = new File(FilePath);
			//RandomAccessFile outfile = new RandomAccessFile(filename,"rw");

			OutputStream outfile = new BufferedOutputStream(new FileOutputStream(filename));
			while (true) {
				filename = new File(FilePath + count + ".sp");
				if (filename.exists()) {
					//RandomAccessFile infile = new RandomAccessFile(filename,"r");
					InputStream infile = new BufferedInputStream(new FileInputStream(filename));
					data = infile.read();
					while (data != -1) {
						outfile.write(data);
						data = infile.read();
					}
					leng++;
					infile.close();
					count++;
				} else {
					break;
				}
			}
			outfile.close();
			 endTime= new Date().getTime() - startTime;
			 System.out.println("\n Total time taken : " + endTime +" Mili seconds");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
