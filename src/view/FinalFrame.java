package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import model.Player;

public class FinalFrame extends JFrame {

	private List<Player> players;
	private JPanel contentPane;

	public FinalFrame(List<Player> players) {

		this.players = players;

		setTitle("Classifica");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		getContentPane().setBackground(Color.DARK_GRAY); // Sfondo scuro per il frame

		// Pannello per la classifica
		JPanel rankingPanel = new JPanel();
		rankingPanel.setLayout(new GridLayout(players.size(), 1));
		rankingPanel.setBackground(Color.DARK_GRAY); // Colore di sfondo del pannello
		rankingPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Aggiunta di bordi

		// Stile del testo per la classifica
		Font rankingFont = new Font("Arial", Font.BOLD, 22);

		// Caricamento dell'immagine del trofeo
		ImageIcon trophyIcon = new ImageIcon("./assets/myshelfie/rank/trophy1.png");
		JLabel trophyLabel = new JLabel(trophyIcon);

		// Etichette per i nomi dei giocatori, i loro punti e l'immagine del trofeo
		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			String playerName = player.getPlayerName();
			int playerPoints = player.getPoints();
			String rankingText = (i + 1) + ". " + playerName + " - " + playerPoints + " punti";

			JLabel rankingLabel = new JLabel(rankingText);
			rankingLabel.setFont(rankingFont);
			rankingLabel.setForeground(Color.WHITE);
			rankingPanel.add(rankingLabel);
		}

		// Aggiunta del pannello della classifica al frame
		add(rankingPanel, BorderLayout.CENTER);

		// Aggiunta del trofeo in cima al pannello della classifica
		add(trophyLabel, BorderLayout.NORTH);

		// Impostazione dello stile grafico "Nimbus"
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
