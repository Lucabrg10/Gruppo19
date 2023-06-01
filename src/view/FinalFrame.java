package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageConsumer;
import java.awt.image.ImageProducer;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import model.ColorTile;
import model.Player;
import model.Tile;

public class FinalFrame extends JFrame {

	private List<Player> players;
	private JPanel contentPane;

	public FinalFrame(List<Player> players) {

		this.players = players;

		for (Player player : players) {
			player.addPoints(player.getShelf().countPointsOfAlignedTiles());
		}
		rankPlayers(players);

		JDialog dialog = new JDialog();
		JLabel lbLoading = new JLabel("Calcolo del punteggio finale...");
		lbLoading.setHorizontalAlignment(SwingConstants.CENTER);
		lbLoading.setFont(lbLoading.getFont().deriveFont(Font.BOLD, 16f));

		JProgressBar progressBar = new JProgressBar();
		progressBar.setPreferredSize(new Dimension(200, 30));

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(lbLoading, BorderLayout.CENTER);
		panel.add(progressBar, BorderLayout.SOUTH);

		dialog.add(panel);
		dialog.setPreferredSize(new Dimension(300, 150));
		dialog.pack();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);

		Timer timer = new Timer(20, new ActionListener() {
			int progress = 0;

			@Override
			public void actionPerformed(ActionEvent e) {
				progress++;
				progressBar.setValue(progress);

				if (progress >= 100) {
					((Timer) e.getSource()).stop();
					dialog.dispose();
					setTitle("Classifica");
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					setLayout(new BorderLayout());
					getContentPane().setBackground(Color.DARK_GRAY); // Sfondo scuro per il frame

					// Pannello per la classifica
					JPanel rankingPanel = new JPanel();
					rankingPanel.setLayout(new BoxLayout(rankingPanel, BoxLayout.Y_AXIS));
					rankingPanel.setBackground(Color.DARK_GRAY); // Colore di sfondo del pannello
					rankingPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Aggiunta di bordi

					// Stile del testo per la classifica
					Font rankingFont = new Font("Arial", Font.BOLD, 20);

					// Caricamento dell'immagine del trofeo
					ImageIcon trophyIcon = new ImageIcon("./assets/myshelfie/rank/trophy1.png");
					JLabel trophyLabel = new JLabel(trophyIcon);

					ColorTile colorTile[] = ColorTile.values();
					// Etichette per i nomi dei giocatori, i loro punti e l'immagine del trofeo
					for (int i = 0; i < players.size(); i++) {
						Player player = players.get(i);
						String playerName = player.getPlayerName();
						int playerPoints = player.getPoints();
						String rankingText = (i + 1) + ". " + playerName + " - " + playerPoints + " punti";

						JLabel rankingLabel = new JLabel(new Tile(colorTile[5 - i]).getImg());

						rankingLabel.setFont(rankingFont);
						rankingLabel.setAlignmentX(CENTER_ALIGNMENT);
						rankingLabel.setForeground(Color.WHITE);
						rankingLabel.setText(rankingText);
						rankingLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
						rankingPanel.add(rankingLabel);

					}

					// Aggiunta del pannello della classifica al frame
					add(rankingPanel, BorderLayout.CENTER);

					// Aggiunta del trofeo in cima al pannello della classifica
					add(trophyLabel, BorderLayout.NORTH);
					setVisible(true);
					setLocationRelativeTo(null);
					setResizable(false);
				}
			}
		});
		timer.start();

		/*
		 * try { UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		 * } catch (Exception e) { e.printStackTrace(); }
		 */

	}

	public void rankPlayers(List<Player> players) {
		Collections.sort(players, Comparator.comparingInt(Player::getPoints).reversed());
	}
}
