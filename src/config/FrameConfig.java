package config;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

public class FrameConfig implements Serializable{
	
	private final String title;
	
	private final int windowUp;
	
	private final int width;
	
	private final int height;
	
	private final int padding;
	
	private final int border;
	
	private final int sizeRol;
	
	private final int loseIdx;
	
	//ͼ������
	private List<LayerConfig> layersConfig;
	
	//��ť����
	private final ButtonConfig buttonConfig;
	
	public FrameConfig(Element frame) {
		// TODO Auto-generated constructor stub
		//��ȡ���ڿ��
		this.width=Integer.parseInt(frame.attributeValue("width"));
		//��ȡ���ڸ߶�
		this.height=Integer.parseInt(frame.attributeValue("height"));
		//��ȡ�߿��ϸ
		this.border=Integer.parseInt(frame.attributeValue("border"));
		//��ȡ�߿���ڱ߾�
		this.padding=Integer.parseInt(frame.attributeValue("padding"));
		//��ȡ���ڵİθ�
		this.windowUp=Integer.parseInt(frame.attributeValue("windowUp"));
		//��Ϸʧ��ͼƬ
		this.loseIdx=Integer.parseInt(frame.attributeValue("loseIdx"));
		//��ȡ����
		this.title=frame.attributeValue("title");
		//��λ��ƫ����
		this.sizeRol=Integer.parseInt(frame.attributeValue("sizeRol"));
		//��ȡ��������
		List<Element> layers=frame.elements("layer");
		layersConfig=new ArrayList<LayerConfig>();
		//��ȡ���д�������
		for(Element layer: layers)
		{
			//����Ҫ���Ĳ������
			LayerConfig lc=new LayerConfig(
					layer.attributeValue("className"),
					Integer.parseInt(layer.attributeValue("x")),
					Integer.parseInt(layer.attributeValue("y")),
					Integer.parseInt(layer.attributeValue("w")),
					Integer.parseInt(layer.attributeValue("h"))
					);
			layersConfig.add(lc);
		}
		//��ʼ����ť����
		buttonConfig=new ButtonConfig(frame.element("button"));
	}

	public ButtonConfig getButtonConfig() {
		return buttonConfig;
	}

	public int getLoseIdx() {
		return loseIdx;
	}

	public int getSizeRol() {
		return sizeRol;
	}

	public List<LayerConfig> getLayersConfig() {
		return layersConfig;
	}

	public void setLayersConfig(List<LayerConfig> layersConfig) {
		this.layersConfig = layersConfig;
	}

	public String getTitle() {
		return title;
	}

	public int getWindowUp() {
		return windowUp;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getPadding() {
		return padding;
	}

	public int getBorder() {
		return border;
	}
}
