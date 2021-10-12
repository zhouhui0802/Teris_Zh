package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class GameConfig  implements Serializable{

	//��������Ҫ�����Ƕ�Ӧframe�������Լ� ����Layer�������
//	private int width;
//	private int height;
//	private int windowSize;
//	private int padding;
//	private int windowUp;
//	private String title;	
	
	public static FrameConfig FRAME_CONFIG=null;
	
	public static DataConfig DATA_CONFIG=null;
	
	public static SystemConfig SYSTEM_CONFIG=null;
	
	private static final boolean IS_DEBUG=true;
	
	static{
		try {
			if(IS_DEBUG)
			{
				//����XML��ȡ��
				SAXReader reader=new SAXReader();
				//��ȡXML�ļ�
				Document doc= reader.read("config/cfg.xml");
				//��ȡXML�ļ��ĸ��ڵ�
				Element game=doc.getRootElement();
				//����������ö���
				FRAME_CONFIG=new FrameConfig(game.element("frame"));
				//�������ݷ������ö���
				DATA_CONFIG=new DataConfig(game.element("data"));
				//����ϵͳ����
				SYSTEM_CONFIG=new SystemConfig(game.element("system"));
			}else
			{
				ObjectInputStream ois;
				try {
					ois = new ObjectInputStream(new FileInputStream("data/framecfg.dat"));
					FRAME_CONFIG=(FrameConfig)ois.readObject();
					ois=new ObjectInputStream(new FileInputStream("data/systemcfg.dat"));
					SYSTEM_CONFIG=(SystemConfig)ois.readObject();
					ois=new ObjectInputStream(new FileInputStream("data/datacfg.dat"));
					DATA_CONFIG=(DataConfig)ois.readObject();
					ois.close();
					System.out.println("��ȡ�ɹ�");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			
			}

		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	private GameConfig()
	{
		
	}
	
	//��ô�������
	public static FrameConfig getFrameConfig()
	{
		return FRAME_CONFIG;
	}
	//���ϵͳ����
	public static SystemConfig getSystemConfig()
	{
		return SYSTEM_CONFIG;
	}
	//������ݷ�������
	public static DataConfig getDataConfig()
	{
		return DATA_CONFIG;
	}

//	public static void main(String[] args) throws Exception
//	{
//		ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("data/framecfg.dat"));
//		oos.writeObject(FRAME_CONFIG);
//		oos=new ObjectOutputStream(new FileOutputStream("data/systemcfg.dat"));
//		oos.writeObject(SYSTEM_CONFIG);
//		oos=new ObjectOutputStream(new FileOutputStream("data/datacfg.dat"));
//		oos.writeObject(DATA_CONFIG);
//		System.out.println("д��ɹ�");
//	}




}
