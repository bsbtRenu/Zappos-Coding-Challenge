/************************************************************************************
 * File             :   PriceNotificationFrame.java
 * Description      :   This class is used to design GUI for the application
 ************************************************************************************/

package com.zappos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class PriceNotificationFrame extends JFrame {

	private PriceNotificationInvokeZAPI invAPI;
	private PriceNotifProdDetDTO prodDTO;
	private FamilyCoreValues coreValueDTO;
	private AddToFavorites addToFav;
	UserDetailsPanel userPanel;
	URL imgURL = PriceNotificationFrame.class.getResource("windowLogo.png");

	public static void main(String args[]) {
		new PriceNotificationFrame("Get Price Notifications for your favorite products on Zappos!");
	}

	public PriceNotificationFrame() {
		super("Default");
	}
	
	//For Window Settings
	public PriceNotificationFrame(String msg) {
		super(msg);

		userPanel = new UserDetailsPanel();
		setSize(600, 700);
		setContentPane(userPanel);
		setIconImage(new ImageIcon(imgURL).getImage());
		setDefaultLookAndFeelDecorated(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setLayout(new FlowLayout());
		setLocationRelativeTo(null);
		setBounds(400, 200, 600, 500);
		setBackground(Color.WHITE);

	}

	//This panel is designed for the input from user
	public class UserDetailsPanel extends JPanel {
		private JLabel labelEmailID;
		private JLabel labelProdID;
		private JTextField txtEmail;
		private JTextField txtProd;
		private JButton btnSubmit;
		private JLabel search;
		private BufferedImage prodImg;
		private Pattern pattern;
		private final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		ImageIcon searchIcon = new ImageIcon("Images/searchIcon.png");
		//URL searchIcon = PriceNotificationFrame.class.getResource("searchIcon.png");
		boolean searchFlag = true;
		String prodBrand;
		String prodOp;
		String prodPercentOff;
		String prodId;
		String prodName;
		URL prodURL;
		String prodPrice;
		boolean blankText = false;
		boolean emailFlag = false;
		boolean validEmailFlg = false;
		boolean addFavFlg = false;

		public UserDetailsPanel() {
			super();

			setLayout(null);
			setBackground(Color.WHITE);

			//Email ID Label
			labelEmailID = new JLabel();
			labelEmailID.setText("Email id");
			labelEmailID.setVisible(true);
			labelEmailID.setPreferredSize(new Dimension(175, 30));
			labelEmailID.setVisible(false);

			//Email ID Texbox
			txtEmail = new JTextField();
			txtEmail.setForeground(Color.BLACK);
			txtEmail.setPreferredSize(new Dimension(200, 20));
			txtEmail.setVisible(false);

			//Product ID Label
			labelProdID = new JLabel();
			labelProdID.setText("        Favorite Product");
			labelProdID.setVisible(true);
			labelProdID.setPreferredSize(new Dimension(200, 50));

			//Product ID Text Box
			txtProd = new JTextField();
			txtProd.setForeground(Color.BLACK);
			txtProd.setPreferredSize(new Dimension(200, 20));
			
			//Search icon
			search = new JLabel(searchIcon);
			search.setPreferredSize(new Dimension(23, 30));
			search.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent me) {
					if(txtProd.getText().length()==0){
						blankText = true;
					}else{
					getProductDetails(txtProd.getText());
					}
					repaint();
				}

			});
			
				

			//Notify Button			
			btnSubmit = new JButton();
			btnSubmit.setText("Notify me when this product is on sale");
			btnSubmit.setPreferredSize(new Dimension(300, 25));
			btnSubmit.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println(txtEmail.getText());
					try {
						pattern = Pattern.compile(EMAIL_PATTERN);
						validEmailFlg =(pattern.matcher(txtEmail.getText()).matches());
						if(txtEmail.getText().length()!=0 && validEmailFlg){
							prodDTO.setMailId(txtEmail.getText());
							addToFavorites(prodDTO);
						}else{
							emailFlag = true;
							repaint();
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			});
			btnSubmit.setVisible(false);
			btnSubmit.setBackground(new Color(255, 123, 6));
			
			getCoreValues();
			
			

			this.add(labelEmailID);
			this.add(txtEmail);
			this.add(labelProdID);
			this.add(txtProd);
			this.add(search);
			this.add(btnSubmit);
		}

		//To fetch a random core value
		private void getCoreValues() {
			System.out.println("Fetching Core Value");
			invAPI = new PriceNotificationInvokeZAPI();
			prodDTO = new PriceNotifProdDetDTO();
			coreValueDTO = invAPI.getRandomCoreValue();
		}

		//To fetch details of the product searched
		private void getProductDetails(String text) {	
			System.out.println("Product Entered: "+text);
			invAPI = new PriceNotificationInvokeZAPI();
			prodDTO = new PriceNotifProdDetDTO();
			try {
				prodDTO = invAPI.getProdDetFrmAPI(text);
				if(prodDTO.getProductId() != null){
					prodImg = ImageIO.read(prodDTO.getThumbnailImageUrl());
					prodBrand = prodDTO.getBrandName();
					prodOp = prodDTO.getOriginalPrice();
					prodPercentOff = prodDTO.getPercentOff();
					prodId = prodDTO.getProductId();
					prodName = prodDTO.getProductName();
					prodURL = prodDTO.getProductUrl();
					prodPrice = prodDTO.getPrice();
					btnSubmit.setVisible(true);
					labelEmailID.setVisible(true);
					txtEmail.setVisible(true);
					
				}
				searchFlag = prodDTO.isSearchFlag();
				
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		//Paint components on JFrame
		protected void paintComponent(Graphics g) {				
			super.paintComponent(g);
			//If Product Text box is blank
			if(blankText){
				g.setColor(Color.RED);
				g.drawString("Please enter a product",282,80);				
				g.setColor(Color.black);
				blankText = false;
			}
			//If Email id Text box is blank
			if(emailFlag){
				System.out.println("Hello");
				g.setColor(Color.RED);
				g.drawString("Please enter a valid Email Id",282,42);				
				g.setColor(Color.black);
				emailFlag = false;
			}
			//If search succeeds
			if(prodDTO.getErrorMsg()==null){
				if(searchFlag){
						if (prodImg != null) {
							g.drawImage(prodImg, 10, 160, null);
							g.drawString("Brand: "+prodBrand,150,175);
							g.drawString("Product Name: "+prodName,150,190);
							g.drawString("Original Price: "+prodOp,150,205);
							if(Float.parseFloat(prodPercentOff.substring(0, (prodPercentOff.length()-1))) > 0){
								g.setColor(Color.RED);
								g.drawString("Percent Off: "+prodPercentOff,150,220);
								g.drawString("Price: "+prodPrice,150,235);
								g.setColor(Color.BLACK);
							}else{
								g.drawString("Percent Off: "+prodPercentOff,150,220);
								g.drawString("Price: "+prodPrice,150,235);
							}
							g.drawString("URL : "+prodURL,150,250);
							g.setColor(Color.blue);
							g.drawString("Be \"in-the-know\" when this product is on Sale! Simply sign up and we'll notify you.", 80, 145);
						}
					}else {		
						System.out.println("Oops! No Products found");
						g.setColor(Color.blue);
						g.drawString("Oops! No Products found", 150, 160);				
					}
			}else{
				g.setColor(Color.RED);
				g.drawString(prodDTO.getErrorMsg(), 150, 160);
			}
			//Core Value fetch succeeds
			if(coreValueDTO.getName()!=""){
				g.setColor(Color.BLUE);
				//g.setFont(new Font("Sans Serif", Font.BOLD, 13));
				g.drawString("ZAPPOS FAMILY CORE VALUE: "+coreValueDTO.getId()+"."+coreValueDTO.getName(), 10, 440);
				
			}
			//Added to Favorites!
			if(addFavFlg){
				g.setColor(Color.GREEN);
				g.drawString("THANK YOU FOR YOUR NOTIFICATION REQUEST!", 150, 350);
				g.setFont(new Font("Calibri", Font.BOLD, 13));
			}

		}
		
		// Add to Favorites
		private void addToFavorites(PriceNotifProdDetDTO prodDTO)throws IOException {
			invAPI = new PriceNotificationInvokeZAPI();
			addToFav = new AddToFavorites();
			prodDTO.setNotifyFlag(false);
			addFavFlg = addToFav.writeToJson(prodDTO);
			repaint();

		}

	}

}
