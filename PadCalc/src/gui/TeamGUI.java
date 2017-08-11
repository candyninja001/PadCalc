package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import data.Card;
import data.TeamCoop;

public class TeamGUI extends JFrame implements ActionListener {
	private JPanelEditCard cardEditor;
	private JLabel teamDataLabel;
	private JTextField teamData;
	private JButton load;
	private JButton save;
	
	private JButton card0; // Actual cards
	private JButton card1;
	private JButton card2;
	private JButton card3;
	private JButton card4;
	private JButton card5;
	private JButton card6;
	private JButton card7;
	private JButton card8;
	private JButton card9;
	private JButton card10; // Inherits for cards
	private JButton card11;
	private JButton card12;
	private JButton card13;
	private JButton card14;
	private JButton card15;
	private JButton card16;
	private JButton card17;
	private JButton card18;
	private JButton card19;

	private TeamCoop team;
	private int currentCard = 0;

	public TeamGUI() {
		team = new TeamCoop();
		initComponents();
	}

	private void initComponents() {
		 setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		 setTitle("PAD Team Calculator");
		 
		 JPanel teamEditorPanel = new JPanel();
		 teamEditorPanel.setLayout(new BoxLayout(teamEditorPanel, BoxLayout.Y_AXIS));
		 
		 JPanel header = new JPanel();
		 header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));
		 
		 teamDataLabel = new JLabel("TeamData");
		 header.add(teamDataLabel);
		 
		 teamData = new JTextField("");
		 teamData.setMaximumSize(new Dimension(400, 30));
		 teamData.setPreferredSize(new Dimension(400, 30));
		 teamData.setMinimumSize(new Dimension(400, 30));
		 teamData.setActionCommand("teamData");
		 teamData.addActionListener(this);
		 header.add(teamData);
		 
		 load = new JButton("Load Team");
		 load.setActionCommand("loadTeam");
		 load.addActionListener(this);
		 header.add(load);
		 
		 save = new JButton("Save Team");
		 save.setActionCommand("saveTeam");
		 save.addActionListener(this);
		 header.add(save);
		 teamEditorPanel.add(header);
		 
		 
		 JPanel teamPanel = new JPanel();
		 teamPanel.setLayout(new BoxLayout(teamPanel, BoxLayout.Y_AXIS));
		 JPanel p1InheritPanel = new JPanel();
		 p1InheritPanel.setLayout(new BoxLayout(p1InheritPanel, BoxLayout.X_AXIS));
		 card10 = new JButton();
		 card10.setActionCommand("selectCard10");
		 card10.addActionListener(this);
		 loadButtonImage(card10, team.getCard(10));
		 p1InheritPanel.add(card10);
		 card11 = new JButton();
		 card11.setActionCommand("selectCard11");
		 card11.addActionListener(this);
		 loadButtonImage(card11, team.getCard(11));
		 p1InheritPanel.add(card11);
		 card12 = new JButton();
		 card12.setActionCommand("selectCard12");
		 card12.addActionListener(this);
		 loadButtonImage(card12, team.getCard(12));
		 p1InheritPanel.add(card12);
		 card13 = new JButton();
		 card13.setActionCommand("selectCard13");
		 card13.addActionListener(this);
		 loadButtonImage(card13, team.getCard(13));
		 p1InheritPanel.add(card13);
		 card14 = new JButton();
		 card14.setActionCommand("selectCard14");
		 card14.addActionListener(this);
		 loadButtonImage(card14, team.getCard(14));
		 p1InheritPanel.add(card14);
		 teamPanel.add(p1InheritPanel);
		 
		 JPanel p1TeamPanel = new JPanel();
		 p1TeamPanel.setLayout(new BoxLayout(p1TeamPanel, BoxLayout.X_AXIS));
		 card0 = new JButton();
		 card0.setActionCommand("selectCard0");
		 card0.addActionListener(this);
		 loadButtonImage(card0, team.getCard(0));
		 p1TeamPanel.add(card0);
		 card1 = new JButton();
		 card1.setActionCommand("selectCard1");
		 card1.addActionListener(this);
		 loadButtonImage(card1, team.getCard(1));
		 p1TeamPanel.add(card1);
		 card2 = new JButton();
		 card2.setActionCommand("selectCard2");
		 card2.addActionListener(this);
		 loadButtonImage(card2, team.getCard(2));
		 p1TeamPanel.add(card2);
		 card3 = new JButton();
		 card3.setActionCommand("selectCard3");
		 card3.addActionListener(this);
		 loadButtonImage(card3, team.getCard(3));
		 p1TeamPanel.add(card3);
		 card4 = new JButton();
		 card4.setActionCommand("selectCard4");
		 card4.addActionListener(this);
		 loadButtonImage(card4, team.getCard(4));
		 p1TeamPanel.add(card4);
		 teamPanel.add(p1TeamPanel);
		 
		 JPanel p2TeamPanel = new JPanel();
		 p2TeamPanel.setLayout(new BoxLayout(p2TeamPanel, BoxLayout.X_AXIS));
		 card5 = new JButton();
		 card5.setActionCommand("selectCard5");
		 card5.addActionListener(this);
		 loadButtonImage(card5, team.getCard(5));
		 p2TeamPanel.add(card5);
		 card6 = new JButton();
		 card6.setActionCommand("selectCard6");
		 card6.addActionListener(this);
		 loadButtonImage(card6, team.getCard(6));
		 p2TeamPanel.add(card6);
		 card7 = new JButton();
		 card7.setActionCommand("selectCard7");
		 card7.addActionListener(this);
		 loadButtonImage(card7, team.getCard(7));
		 p2TeamPanel.add(card7);
		 card8 = new JButton();
		 card8.setActionCommand("selectCard8");
		 card8.addActionListener(this);
		 loadButtonImage(card8, team.getCard(8));
		 p2TeamPanel.add(card8);
		 card9 = new JButton();
		 card9.setActionCommand("selectCard9");
		 card9.addActionListener(this);
		 loadButtonImage(card9, team.getCard(9));
		 p2TeamPanel.add(card9);
		 teamPanel.add(p2TeamPanel);
		 
		 JPanel p2InheritPanel = new JPanel();
		 p2InheritPanel.setLayout(new BoxLayout(p2InheritPanel, BoxLayout.X_AXIS));
		 card15 = new JButton();
		 card15.setActionCommand("selectCard15");
		 card15.addActionListener(this);
		 loadButtonImage(card15, team.getCard(15));
		 p2InheritPanel.add(card15);
		 card16 = new JButton();
		 card16.setActionCommand("selectCard16");
		 card16.addActionListener(this);
		 loadButtonImage(card16, team.getCard(16));
		 p2InheritPanel.add(card16);
		 card17 = new JButton();
		 card17.setActionCommand("selectCard17");
		 card17.addActionListener(this);
		 loadButtonImage(card17, team.getCard(17));
		 p2InheritPanel.add(card17);
		 card18 = new JButton();
		 card18.setActionCommand("selectCard18");
		 card18.addActionListener(this);
		 loadButtonImage(card18, team.getCard(18));
		 p2InheritPanel.add(card18);
		 card19 = new JButton();
		 card19.setActionCommand("selectCard19");
		 card19.addActionListener(this);
		 loadButtonImage(card19, team.getCard(19));
		 p2InheritPanel.add(card19);
		 teamPanel.add(p2InheritPanel);
		 teamEditorPanel.add(teamPanel);
		 
		 cardEditor = new JPanelEditCard(team.getCard(currentCard)){
			 public void onCardUpdate(){
				 team.setCard(currentCard, this.card);
				 switch(currentCard){
				 	case 0: loadButtonImage(card0, team.getCard(0));
				 	return;
				 	case 1: loadButtonImage(card1, team.getCard(1));
			 		return;
				 	case 2: loadButtonImage(card2, team.getCard(2));
			 		return;
				 	case 3: loadButtonImage(card3, team.getCard(3));
			 		return;
				 	case 4: loadButtonImage(card4, team.getCard(4));
			 		return;
				 	case 5: loadButtonImage(card5, team.getCard(5));
			 		return;
				 	case 6: loadButtonImage(card6, team.getCard(6));
			 		return;
				 	case 7: loadButtonImage(card7, team.getCard(7));
			 		return;
				 	case 8: loadButtonImage(card8, team.getCard(8));
			 		return;
				 	case 9: loadButtonImage(card9, team.getCard(9));
			 		return;
				 	case 10: loadButtonImage(card10, team.getCard(10));
			 		return;
				 	case 11: loadButtonImage(card11, team.getCard(11));
			 		return;
				 	case 12: loadButtonImage(card12, team.getCard(12));
			 		return;
				 	case 13: loadButtonImage(card13, team.getCard(13));
			 		return;
				 	case 14: loadButtonImage(card14, team.getCard(14));
			 		return;
				 	case 15: loadButtonImage(card15, team.getCard(15));
			 		return;
				 	case 16: loadButtonImage(card16, team.getCard(16));
			 		return;
				 	case 17: loadButtonImage(card17, team.getCard(17));
			 		return;
				 	case 18: loadButtonImage(card18, team.getCard(18));
			 		return;
				 	case 19: loadButtonImage(card19, team.getCard(19));
			 		return;
				 }
			 }
		 };
		 
		 cardEditor.setLayout(new FlowLayout());
		 teamEditorPanel.add(cardEditor);
		 add(teamEditorPanel);
		 setSize(1250, 800);
		 //setLocationRelativeTo(null);
		 setVisible(true);
	}
	
	public void loadButtonImage(JButton object, Card card){
		object.setBorderPainted(false);
		object.setBorder(null);
		//button.setFocusable(false);
		object.setMargin(new Insets(0, 0, 0, 0));
		object.setContentAreaFilled(false);
		object.setMaximumSize(new Dimension(100, 100));
		object.setPreferredSize(new Dimension(100, 100));
		object.setMinimumSize(new Dimension(100, 100));
		object.setIcon(new ImageIcon(card.getIcon()));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("selectCard0")) {
			team.setCard(currentCard, cardEditor.card);
			currentCard = 0;
			cardEditor.card = team.getCard(currentCard);
			cardEditor.updatePanel(true);
		}else if (e.getActionCommand().equals("selectCard1")) {
			team.setCard(currentCard, cardEditor.card);
			currentCard = 1;
			cardEditor.card = team.getCard(currentCard);
			cardEditor.updatePanel(true);
		}else if (e.getActionCommand().equals("selectCard2")) {
			team.setCard(currentCard, cardEditor.card);
			currentCard = 2;
			cardEditor.card = team.getCard(currentCard);
			cardEditor.updatePanel(true);
		}else if (e.getActionCommand().equals("selectCard3")) {
			team.setCard(currentCard, cardEditor.card);
			currentCard = 3;
			cardEditor.card = team.getCard(currentCard);
			cardEditor.updatePanel(true);
		}else if (e.getActionCommand().equals("selectCard4")) {
			team.setCard(currentCard, cardEditor.card);
			currentCard = 4;
			cardEditor.card = team.getCard(currentCard);
			cardEditor.updatePanel(true);
		}else if (e.getActionCommand().equals("selectCard5")) {
			team.setCard(currentCard, cardEditor.card);
			currentCard = 5;
			cardEditor.card = team.getCard(currentCard);
			cardEditor.updatePanel(true);
		}else if (e.getActionCommand().equals("selectCard6")) {
			team.setCard(currentCard, cardEditor.card);
			currentCard = 6;
			cardEditor.card = team.getCard(currentCard);
			cardEditor.updatePanel(true);
		}else if (e.getActionCommand().equals("selectCard7")) {
			team.setCard(currentCard, cardEditor.card);
			currentCard = 7;
			cardEditor.card = team.getCard(currentCard);
			cardEditor.updatePanel(true);
		}else if (e.getActionCommand().equals("selectCard8")) {
			team.setCard(currentCard, cardEditor.card);
			currentCard = 8;
			cardEditor.card = team.getCard(currentCard);
			cardEditor.updatePanel(true);
		}else if (e.getActionCommand().equals("selectCard9")) {
			team.setCard(currentCard, cardEditor.card);
			currentCard = 9;
			cardEditor.card = team.getCard(currentCard);
			cardEditor.updatePanel(true);
		}else if (e.getActionCommand().equals("selectCard10")) {
			team.setCard(currentCard, cardEditor.card);
			currentCard = 10;
			cardEditor.card = team.getCard(currentCard);
			cardEditor.updatePanel(true);
		}else if (e.getActionCommand().equals("selectCard11")) {
			team.setCard(currentCard, cardEditor.card);
			currentCard = 11;
			cardEditor.card = team.getCard(currentCard);
			cardEditor.updatePanel(true);
		}else if (e.getActionCommand().equals("selectCard12")) {
			team.setCard(currentCard, cardEditor.card);
			currentCard = 12;
			cardEditor.card = team.getCard(currentCard);
			cardEditor.updatePanel(true);
		}else if (e.getActionCommand().equals("selectCard13")) {
			team.setCard(currentCard, cardEditor.card);
			currentCard = 13;
			cardEditor.card = team.getCard(currentCard);
			cardEditor.updatePanel(true);
		}else if (e.getActionCommand().equals("selectCard14")) {
			team.setCard(currentCard, cardEditor.card);
			currentCard = 14;
			cardEditor.card = team.getCard(currentCard);
			cardEditor.updatePanel(true);
		}else if (e.getActionCommand().equals("selectCard15")) {
			team.setCard(currentCard, cardEditor.card);
			currentCard = 15;
			cardEditor.card = team.getCard(currentCard);
			cardEditor.updatePanel(true);
		}else if (e.getActionCommand().equals("selectCard16")) {
			team.setCard(currentCard, cardEditor.card);
			currentCard = 16;
			cardEditor.card = team.getCard(currentCard);
			cardEditor.updatePanel(true);
		}else if (e.getActionCommand().equals("selectCard17")) {
			team.setCard(currentCard, cardEditor.card);
			currentCard = 17;
			cardEditor.card = team.getCard(currentCard);
			cardEditor.updatePanel(true);
		}else if (e.getActionCommand().equals("selectCard18")) {
			team.setCard(currentCard, cardEditor.card);
			currentCard = 18;
			cardEditor.card = team.getCard(currentCard);
			cardEditor.updatePanel(true);
		}else if (e.getActionCommand().equals("selectCard19")) {
			team.setCard(currentCard, cardEditor.card);
			currentCard = 19;
			cardEditor.card = team.getCard(currentCard);
			cardEditor.updatePanel(true);
		}
//		else if (e.getActionCommand().equals("teamName")) {
//	        team.teamName = teamName.getText();
//	    }
		else if (e.getActionCommand().equals("loadTeam")) {
        	
//            int returnVal = fileChooser.showOpenDialog(this);
//            if (returnVal == JFileChooser.APPROVE_OPTION) {
//                File file = fileChooser.getSelectedFile();
//                team.loadTeam(file);
//            }
        	team.loadTeamData(teamData.getText());
        	cardEditor.card = team.getCard(currentCard);
        	cardEditor.updatePanel(true);
        	loadButtonImage(card0, team.getCard(0));
        	loadButtonImage(card1, team.getCard(1));
        	loadButtonImage(card2, team.getCard(2));
        	loadButtonImage(card3, team.getCard(3));
        	loadButtonImage(card4, team.getCard(4));
        	loadButtonImage(card5, team.getCard(5));
        	loadButtonImage(card6, team.getCard(6));
        	loadButtonImage(card7, team.getCard(7));
        	loadButtonImage(card8, team.getCard(8));
        	loadButtonImage(card9, team.getCard(9));
        	loadButtonImage(card10, team.getCard(10));
        	loadButtonImage(card11, team.getCard(11));
        	loadButtonImage(card12, team.getCard(12));
        	loadButtonImage(card13, team.getCard(13));
        	loadButtonImage(card14, team.getCard(14));
        	loadButtonImage(card15, team.getCard(15));
        	loadButtonImage(card16, team.getCard(16));
        	loadButtonImage(card17, team.getCard(17));
        	loadButtonImage(card18, team.getCard(18));
        	loadButtonImage(card19, team.getCard(19));
        	//cardEditor.onCardUpdate();
        }else if (e.getActionCommand().equals("saveTeam")) {
//            int returnVal = fileChooser.showSaveDialog(this);
//            if (returnVal == JFileChooser.APPROVE_OPTION) {
//                File file = fileChooser.getSelectedFile();
//                team.saveTeam(file);
//            }
        	teamData.setText(team.getTeamData());
        }

	}
}
