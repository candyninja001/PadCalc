package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import data.Card;
import data.EnumAwakening;
import data.EnumType;
import data.Monster;

public class JPanelEditCard extends JPanel implements ActionListener, FocusListener {
	public Card card; 
	
	public JTextField id;
	public JLabel maxLevel;
	public JTextField level;
	public JLabel icon;
	public JLabel type1;
	public JLabel type2;
	public JLabel type3;
	public JLabel awakening1;
	public JLabel awakening2;
	public JLabel awakening3;
	public JLabel awakening4;
	public JLabel awakening5;
	public JLabel awakening6;
	public JLabel awakening7;
	public JLabel awakening8;
	public JLabel awakening9;
	public JTextField plusHP;
	public JTextField plusATK;
	public JTextField plusRCV;
	public JLabel statHP;
	public JLabel statATK;
	public JLabel statRCV;
	public JLabel skillLabel;
	public JTextField skillLevel;
	public JLabel skillLevelStats;
	public JLabel skillLevelMaxStats;
	public JLabel skillDesc;
	
	//Add latent and awakening buttons later;
	
	public JPanelEditCard(){
		card = new Card((Monster)null);
		initComponents();
	}
	
	public JPanelEditCard(Card c){
		card = c;
		initComponents();
	}

	private void initComponents() {
	    // Use Thius: https://docs.oracle.com/javase/tutorial/uiswing/layout/box.html
	    // This will use 7 vertical box layouts, arranged horizontally
	    setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
	    
	    
	    
	    JPanel infoPanel = new JPanel();
	    infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
	    
	    infoPanel.add(new JLabel("ID:"));
	    
	    id = new JTextField();
	    id.setText("" + card.getID());
	    id.setPreferredSize(new Dimension(40, 24));
	    id.setActionCommand("id");
	    id.addActionListener(this);
	    id.addFocusListener(this);
	    infoPanel.add(id);
	    
	    infoPanel.add(new JLabel("Level:"));
	    
	    JPanel levels = new JPanel();
	    levels.setLayout(new BoxLayout(levels, BoxLayout.X_AXIS));
	    level = new JTextField();
	    level.setText("" + card.getMaxLevel());
	    level.setPreferredSize(new Dimension(12, 24));
	    level.setActionCommand("level");
	    level.addActionListener(this);
	    level.addFocusListener(this);
	    levels.add(level);
	    
	    maxLevel = new JLabel("(" + card.getMaxLevel() + ")");
	    levels.add(maxLevel);
	    
	    infoPanel.add(levels);
	    
	    add(infoPanel);
	    
		icon = new JLabel();
		icon.setPreferredSize(new Dimension(100, 100));
		setLabelImage(icon, card.getIcon());
		icon.setToolTipText(card.getName());
		
		add(icon);
		
		
		
		JPanel iconsPanel = new JPanel();
	    iconsPanel.setLayout(new BoxLayout(iconsPanel, BoxLayout.Y_AXIS));
	    
	    JPanel typePanel = new JPanel();
	    typePanel.setLayout(new BoxLayout(typePanel, BoxLayout.X_AXIS));
	    typePanel.setAlignmentX( Component.LEFT_ALIGNMENT );
	    
	    type1 = new JLabel();
		type1.setPreferredSize(new Dimension(32, 32));
		setLabelImage(type1, EnumType.getIcon(getType(0)));
		typePanel.add(type1);
		
		type2 = new JLabel();
		type2.setPreferredSize(new Dimension(32, 32));
		setLabelImage(type2, EnumType.getIcon(getType(1)));
		typePanel.add(type2);
		
		type3 = new JLabel();
		type3.setPreferredSize(new Dimension(32, 32));
		setLabelImage(type3, EnumType.getIcon(getType(1)));
		typePanel.add(type3);
	    
	    iconsPanel.add(typePanel);
	    
	    JPanel awakeningPanel = new JPanel();
	    awakeningPanel.setLayout(new BoxLayout(awakeningPanel, BoxLayout.X_AXIS));
	    awakeningPanel.setAlignmentX( Component.LEFT_ALIGNMENT );
	    
	    awakening1 = new JLabel();
		awakening1.setPreferredSize(new Dimension(32, 32));
		setLabelImage(awakening1, EnumAwakening.getIcon(getAwakening(0)));
		awakeningPanel.add(awakening1);
		
		awakening2 = new JLabel();
		awakening2.setPreferredSize(new Dimension(32, 32));
		setLabelImage(awakening2, EnumAwakening.getIcon(getAwakening(1)));
		awakeningPanel.add(awakening2);
		
		awakening3 = new JLabel();
		awakening3.setPreferredSize(new Dimension(32, 32));
		setLabelImage(awakening3, EnumAwakening.getIcon(getAwakening(2)));
		awakeningPanel.add(awakening3);
		
		awakening4 = new JLabel();
		awakening4.setPreferredSize(new Dimension(32, 32));
		setLabelImage(awakening4, EnumAwakening.getIcon(getAwakening(3)));
		awakeningPanel.add(awakening4);
		
		awakening5 = new JLabel();
		awakening5.setPreferredSize(new Dimension(32, 32));
		setLabelImage(awakening5, EnumAwakening.getIcon(getAwakening(4)));
		awakeningPanel.add(awakening5);
		
		awakening6 = new JLabel();
		awakening6.setPreferredSize(new Dimension(32, 32));
		setLabelImage(awakening6, EnumAwakening.getIcon(getAwakening(5)));
		awakeningPanel.add(awakening6);
		
		awakening7 = new JLabel();
		awakening7.setPreferredSize(new Dimension(32, 32));
		setLabelImage(awakening7, EnumAwakening.getIcon(getAwakening(6)));
		awakeningPanel.add(awakening7);
		
		awakening8 = new JLabel();
		awakening8.setPreferredSize(new Dimension(32, 32));
		setLabelImage(awakening8, EnumAwakening.getIcon(getAwakening(7)));
		awakeningPanel.add(awakening8);
		
		awakening9 = new JLabel();
		awakening9.setPreferredSize(new Dimension(32, 32));
		setLabelImage(awakening9, EnumAwakening.getIcon(getAwakening(8)));
		awakeningPanel.add(awakening9);
		
		iconsPanel.add(awakeningPanel);
		
		add(iconsPanel);
		
		
		
		JPanel plusPanel = new JPanel();
	    plusPanel.setLayout(new BoxLayout(plusPanel, BoxLayout.Y_AXIS));
	    JLabel pl = new JLabel("Pluses:");
	    
	    System.out.println("pre-op");
	    
	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    ge.registerFont(MenuGUI.font);
	    pl.setFont(MenuGUI.font);
	    
	    System.out.println("post-op");
	    
	    plusPanel.add(pl);
	    
	    JPanel plusesHP = new JPanel();
	    plusesHP.setLayout(new BoxLayout(plusesHP, BoxLayout.X_AXIS));
	    plusesHP.add(new JLabel("+"));
	    plusHP = new JTextField();
	    plusHP.setText(""+card.plusHP);
	    plusHP.setPreferredSize(new Dimension(12, 24));
	    plusHP.setActionCommand("plusHP");
	    plusHP.addActionListener(this);
	    plusHP.addFocusListener(this);
	    plusesHP.add(plusHP);
	    plusesHP.add(new JLabel("HP"));
	    plusPanel.add(plusesHP);
	    
	    JPanel plusesATK = new JPanel();
	    plusesATK.setLayout(new BoxLayout(plusesATK, BoxLayout.X_AXIS));
	    plusesATK.add(new JLabel("+"));
	    plusATK = new JTextField();
	    plusATK.setText(""+card.plusATK);
	    plusATK.setPreferredSize(new Dimension(12, 24));
	    plusATK.setActionCommand("plusATK");
	    plusATK.addActionListener(this);
	    plusATK.addFocusListener(this);
	    plusesATK.add(plusATK);
	    plusesATK.add(new JLabel("ATK"));
	    plusPanel.add(plusesATK);
	    
	    JPanel plusesRCV = new JPanel();
	    plusesRCV.setLayout(new BoxLayout(plusesRCV, BoxLayout.X_AXIS));
	    plusesRCV.add(new JLabel("+"));
	    plusRCV = new JTextField();
	    plusRCV.setText(""+card.plusRCV);
	    plusRCV.setPreferredSize(new Dimension(12, 24));
	    plusRCV.setActionCommand("plusRCV");
	    plusRCV.addActionListener(this);
	    plusRCV.addFocusListener(this);
	    plusesRCV.add(plusRCV);
	    plusesRCV.add(new JLabel("RCV"));
	    plusPanel.add(plusesRCV);
	    
	    add(plusPanel);
	    add(Box.createRigidArea(new Dimension(6,0)));
	    
	    
	    
	    JPanel statPanel = new JPanel();
	    statPanel.setLayout(new BoxLayout(statPanel, BoxLayout.Y_AXIS));
	    statPanel.add(new JLabel("Stats:"));
	    statPanel.add(Box.createRigidArea(new Dimension(0,5)));
	    
	    JPanel divisionsPanel = new JPanel();
	    divisionsPanel.setLayout(new BoxLayout(divisionsPanel, BoxLayout.X_AXIS));
	    
	    JPanel division1Panel = new JPanel();
	    division1Panel.setLayout(new BoxLayout(division1Panel, BoxLayout.Y_AXIS));
	    division1Panel.setAlignmentX( Component.RIGHT_ALIGNMENT );
	    
	    statHP = new JLabel();
	    statHP.setText("" + card.getHP(true, false, false, false));
	    statHP.setAlignmentX(Component.RIGHT_ALIGNMENT);
	    division1Panel.add(statHP);
	    division1Panel.add(Box.createRigidArea(new Dimension(0,6)));
	    statATK = new JLabel();
	    statATK.setText("" + card.getATK(true, false, false, false));
	    statATK.setAlignmentX(Component.RIGHT_ALIGNMENT);
	    division1Panel.add(statATK);
	    division1Panel.add(Box.createRigidArea(new Dimension(0,6)));
	    statRCV = new JLabel();
	    statRCV.setText("" + card.getRCV(true, false, false, false));
	    statRCV.setAlignmentX(Component.RIGHT_ALIGNMENT);
	    division1Panel.add(statRCV);
	    
	    divisionsPanel.add(division1Panel);
	    divisionsPanel.add(Box.createRigidArea(new Dimension(4,0)));
	    
	    JPanel division2Panel = new JPanel();
	    division2Panel.setLayout(new BoxLayout(division2Panel, BoxLayout.Y_AXIS));
	    
	    division2Panel.add(new JLabel("HP"));
	    division2Panel.add(Box.createRigidArea(new Dimension(0,6)));
	    division2Panel.add(new JLabel("ATK"));
	    division2Panel.add(Box.createRigidArea(new Dimension(0,6)));
	    division2Panel.add(new JLabel("RCV"));
	    
	    divisionsPanel.add(division2Panel);
	    
	    statPanel.add(divisionsPanel);
	    
	    add(statPanel);
	    
	    
	    
	    JPanel activePanel = new JPanel();
	    activePanel.setLayout(new BoxLayout(activePanel, BoxLayout.Y_AXIS));
	    activePanel.setPreferredSize(new Dimension(400, 100));
//	    public JLabel skillLabel;
//		public JTextField skillLevel;
//		public JLabel skillLevelStats;
//		public JLabel skillLevelMaxStats;
//		public JLabel skillDesc;
	    
	    skillLabel = new JLabel();
	    skillLabel.setText("Active: " + card.getActive().name);
	    activePanel.add(skillLabel);
	    
	    JPanel levelDetailPanel = new JPanel();
	    levelDetailPanel.setLayout(new BoxLayout(levelDetailPanel, BoxLayout.X_AXIS));
	    
	    levelDetailPanel.add(new JLabel("Lv."));
	    skillLevel = new JTextField("" + card.skillLevel);
	    skillLevel.setMaximumSize(new Dimension(24, 24));
	    skillLevel.setMinimumSize(new Dimension(24, 24));
	    skillLevel.setActionCommand("skillLevel");
	    skillLevel.addActionListener(this);
	    skillLevel.addFocusListener(this);
	    levelDetailPanel.add(skillLevel);
	    
	    skillLevelStats = new JLabel();
	    skillLevelStats.setText("(Max "+card.getActive().getMaxSkillLevel()+"), Turn(s): " + card.getActive().getCooldown(Integer.valueOf(skillLevel.getText())));
	    levelDetailPanel.add(skillLevelStats);
	    levelDetailPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
	    activePanel.add(levelDetailPanel);
	    
	    skillDesc = new JLabel();
	    skillDesc.setText("<html>"+card.getActive().effect+"</html>");
	    skillDesc.setMaximumSize(new Dimension(400, 50));
	    skillDesc.setMinimumSize(new Dimension(400, 50));
	    skillDesc.setAlignmentX(Component.LEFT_ALIGNMENT);
	    activePanel.add(skillDesc);
	    
	    add(activePanel);
	}
	
	public EnumType getType(int i){
		if(i < 0 || i >= card.getTyping().length)
			return null;
		return card.getTyping()[i];
	}
	
	public EnumAwakening getAwakening(int i){
		if(i < 0 || i >= card.getAwakenings().length)
			return null;
		return card.getAwakenings()[i];
	}
	
//	public void loadLabelImage(JLabel object, String file){
//		if(file == null)
//			return;
//		object.setIcon(new ImageIcon(ImageLoader.getImage(file)));
//	}
	
	public void setLabelImage(JLabel object, BufferedImage bi){
		if(bi == null)
			return;
		object.setIcon(new ImageIcon(bi));
	}
	
	public void updatePanel(boolean monsterUpdate){
		if(card==null)
			card = new Card((Monster)null);
		if(id.getText().equals("") || Integer.valueOf(id.getText())!=card.getID())
			id.setText("" + card.getID());
		
		if(level.getText().equals("") || Integer.valueOf(level.getText())!=card.level)
			level.setText("" + card.level);
		
		if(plusHP.getText().equals("") || Integer.valueOf(plusHP.getText())!=card.plusHP)
			plusHP.setText("" + card.plusHP);
		
		if(plusATK.getText().equals("") || Integer.valueOf(plusATK.getText())!=card.plusATK)
			plusATK.setText("" + card.plusATK);
		
		if(plusRCV.getText().equals("") || Integer.valueOf(plusRCV.getText())!=card.plusRCV)
			plusRCV.setText("" + card.plusRCV);
		
		skillLabel.setText("Active: " + card.getActive().name);
	    skillLevel.setText("" + card.skillLevel);
	    skillLevelStats.setText("(Max "+card.getActive().getMaxSkillLevel()+"), Turn(s): " + card.getActive().getCooldown(Integer.valueOf(skillLevel.getText())));
	    skillDesc.setText("<html>"+card.getActive().effect+"</html>");
		
		if(monsterUpdate){
			maxLevel.setText("(" + card.getMaxLevel() + ")");
			setLabelImage(icon, card.getIcon());
			icon.setToolTipText(card.getName());
		
			setLabelImage(type1, EnumType.getIcon(getType(0)));
			setLabelImage(type2, EnumType.getIcon(getType(1)));
			setLabelImage(type3, EnumType.getIcon(getType(2)));
			
			setLabelImage(awakening1, EnumAwakening.getIcon(getAwakening(0)));
			setLabelImage(awakening2, EnumAwakening.getIcon(getAwakening(1)));
			setLabelImage(awakening3, EnumAwakening.getIcon(getAwakening(2)));
			setLabelImage(awakening4, EnumAwakening.getIcon(getAwakening(3)));
			setLabelImage(awakening5, EnumAwakening.getIcon(getAwakening(4)));
			setLabelImage(awakening6, EnumAwakening.getIcon(getAwakening(5)));
			setLabelImage(awakening7, EnumAwakening.getIcon(getAwakening(6)));
			setLabelImage(awakening8, EnumAwakening.getIcon(getAwakening(7)));
			setLabelImage(awakening9, EnumAwakening.getIcon(getAwakening(8)));
		}
		
		statHP.setText("" + card.getHP(true, false, false, false));
	    statATK.setText("" + card.getATK(true, false, false, false));
	    statRCV.setText("" + card.getRCV(true, false, false, false));
		
	    onCardUpdate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("id")){
			if(id.getText().equals(""))
				id.setText(""+card.getID());
			else if(Integer.valueOf(id.getText()) != null && Integer.valueOf(id.getText()) > 0 && Integer.valueOf(id.getText()) < Monster.monsters.size() && Monster.monsters.get(Integer.valueOf(id.getText())) != null){
				if(Monster.monsters.get(Integer.valueOf(id.getText())) != null){
					card = new Card(Monster.monsters.get(Integer.valueOf(id.getText())));
					card.setAssumed();
					updatePanel(true);
				}
			}else if(Integer.valueOf(id.getText()) == 0){
				card = new Card((Monster)null);
				updatePanel(true);
			}else{
				id.setText(""+card.getID());
			}
		}else if(e.getActionCommand().equals("level")){
			if(!level.getText().equals("") && Integer.valueOf(level.getText())>0 && Integer.valueOf(level.getText())<=card.getMaxLevel())
				card.level=Integer.valueOf(level.getText());
			updatePanel(false);
		}else if(e.getActionCommand().equals("plusHP")){
			if(!plusHP.getText().equals("") && Integer.valueOf(plusHP.getText())>=0 && Integer.valueOf(plusHP.getText())<=99)
				card.plusHP=Integer.valueOf(plusHP.getText());
			updatePanel(false);
		}else if(e.getActionCommand().equals("plusATK")){
			if(!plusATK.getText().equals("") && Integer.valueOf(plusATK.getText())>=0 && Integer.valueOf(plusATK.getText())<=99)
				card.plusATK=Integer.valueOf(plusATK.getText());
			updatePanel(false);
		}else if(e.getActionCommand().equals("plusRCV")){
			if(!plusRCV.getText().equals("") && Integer.valueOf(plusRCV.getText())>=0 && Integer.valueOf(plusRCV.getText())<=99)
				card.plusRCV=Integer.valueOf(plusRCV.getText());
			updatePanel(false);
		}else if(e.getActionCommand().equals("skillLevel")){
			if(!skillLevel.getText().equals("") && Integer.valueOf(plusRCV.getText())>=1 && Integer.valueOf(plusRCV.getText())<=card.getActive().getMaxSkillLevel())
				card.skillLevel=Integer.valueOf(skillLevel.getText());
			updatePanel(false);
		}
	}
	
	/**
	 * To be overridden when used, for saving card variable after every change.
	 */
	public void onCardUpdate(){
		
	}
	
	public void setCard(Card c){
		card = c;
		updatePanel(true);
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		if(e.getSource() == this.id){
			if(id.getText().equals(""))
				id.setText(""+card.getID());
			else if(Integer.valueOf(id.getText()) != null && Integer.valueOf(id.getText()) > 0 && Integer.valueOf(id.getText()) < Monster.monsters.size() && Monster.monsters.get(Integer.valueOf(id.getText())) != null){
				if(Monster.monsters.get(Integer.valueOf(id.getText())) != null){
					card = new Card(Monster.monsters.get(Integer.valueOf(id.getText())));
					card.setAssumed();
					updatePanel(true);
				}
			}else if(Integer.valueOf(id.getText()) == 0){
				card = new Card((Monster)null);
				updatePanel(true);
			}else{
				id.setText(""+card.getID());
			}
		}else if(e.getSource() == this.level){
			if(!level.getText().equals("") && Integer.valueOf(level.getText())>0 && Integer.valueOf(level.getText())<=card.getMaxLevel())
				card.level=Integer.valueOf(level.getText());
			updatePanel(false);
		}else if(e.getSource() == this.plusHP){
			if(!plusHP.getText().equals("") && Integer.valueOf(plusHP.getText())>=0 && Integer.valueOf(plusHP.getText())<=99)
				card.plusHP=Integer.valueOf(plusHP.getText());
			updatePanel(false);
		}else if(e.getSource() == this.plusATK){
			if(!plusATK.getText().equals("") && Integer.valueOf(plusATK.getText())>=0 && Integer.valueOf(plusATK.getText())<=99)
				card.plusATK=Integer.valueOf(plusATK.getText());
			updatePanel(false);
		}else if(e.getSource() == this.plusRCV){
			if(!plusRCV.getText().equals("") && Integer.valueOf(plusRCV.getText())>=0 && Integer.valueOf(plusRCV.getText())<=99)
				card.plusRCV=Integer.valueOf(plusRCV.getText());
			updatePanel(false);
		}else if(e.getSource() == this.skillLevel){
			if(!skillLevel.getText().equals("") && Integer.valueOf(plusRCV.getText())>=1 && Integer.valueOf(plusRCV.getText())<=card.getActive().getMaxSkillLevel())
				card.skillLevel=Integer.valueOf(skillLevel.getText());
			updatePanel(false);
		}
	}
}
