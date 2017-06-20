package com.it.main;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

	private MainMenu mainMenu;
	private CharacterMenu characterMenu;
	private Game game;

	public MouseInput(MainMenu mainMenu, Game game, CharacterMenu characterMenu) {
		this.mainMenu = mainMenu;
		this.game = game;
		this.characterMenu = characterMenu;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		Rectangle mouse = new Rectangle(x, y, 1, 1);

		if (Game.State == Game.STATE.MainMenu) {
			Rectangle playButton = new Rectangle(0, Game.HEIGHT / 4, Game.WIDTH, Game.HEIGHT / 4);
			Rectangle exitButton = new Rectangle(0, Game.HEIGHT / 2 + Game.HEIGHT / 4, Game.WIDTH, Game.HEIGHT / 4);
			if (playButton.intersects(mouse)) {

				mainMenu.startPlayAnimation();

			}
			if (exitButton.intersects(mouse)) {
				System.exit(0);
			}
		} else if (Game.State == Game.STATE.PlayMenu) {
			Rectangle newGame = new Rectangle(0, Game.HEIGHT / 4, Game.WIDTH, Game.HEIGHT / 4);
			Rectangle loadGame = new Rectangle(0, Game.HEIGHT / 2, Game.WIDTH, Game.HEIGHT / 4);
			Rectangle backButton = new Rectangle(0, Game.HEIGHT / 2 + Game.HEIGHT / 4, Game.WIDTH, Game.HEIGHT / 4);
			if (backButton.intersects(mouse)) {
				Game.State = Game.STATE.MainMenu;
			}
			if (loadGame.intersects(mouse)) {
				Game.State = Game.STATE.CharacterMenu;
			}
			if (newGame.intersects(mouse)) {
				Game.State = Game.STATE.Game;
			}
		} else if (Game.State == Game.STATE.GameOver) {
			Rectangle tryAgain = new Rectangle(Game.WIDTH / 4, Game.HEIGHT / 4, Game.WIDTH / 2, Game.HEIGHT / 4);
			Rectangle mainMenu = new Rectangle(Game.WIDTH / 4, Game.HEIGHT / 2, Game.WIDTH / 2, Game.HEIGHT / 4);

			if (mainMenu.intersects(mouse)) {
				Game.State = Game.STATE.MainMenu;
			}
			if (tryAgain.intersects(mouse)) {
				game.loadNewLevel();
				Game.State = Game.STATE.Game;
			}
		} else if (Game.State == Game.STATE.CharacterMenu) {
			Rectangle jaime = new Rectangle(0, 0, Game.WIDTH / 4, Game.HEIGHT / 4);
			Rectangle jaime1 = new Rectangle(Game.WIDTH / 4, 0, Game.WIDTH / 4, Game.HEIGHT / 4);
			Rectangle jaime2 = new Rectangle(Game.WIDTH / 2, 0, Game.WIDTH / 4, Game.HEIGHT / 4);
			Rectangle jaime3 = new Rectangle(Game.WIDTH / 2 + Game.WIDTH / 4, 0, Game.WIDTH / 4, Game.HEIGHT / 4);
			if (jaime.intersects(mouse)) {
				characterMenu.setSelectedCharacter(CharacterType.Jaime);
			}
			if (jaime1.intersects(mouse)) {

			}
			if (jaime2.intersects(mouse)) {

			}
			if (jaime3.intersects(mouse)) {

			}
			
			if(characterMenu.getSelectedCharacter()!=null){
				Rectangle level0 = new Rectangle(0,Game.HEIGHT/4,Game.WIDTH/4,(3*Game.HEIGHT/4)/2);
				Rectangle level1 = new Rectangle(Game.WIDTH/4,Game.HEIGHT/4,Game.WIDTH/4,(3*Game.HEIGHT/4)/2);
				Rectangle level2 = new Rectangle(Game.WIDTH/2,Game.HEIGHT/4,Game.WIDTH/4,(3*Game.HEIGHT/4)/2);
//				Rectangle level3 = new Rectangle(Game.WIDTH / 2 + Game.WIDTH / 4,Game.HEIGHT/4,Game.WIDTH/4,(3*Game.HEIGHT/4)/2);
//				Rectangle level4 = new Rectangle(0,((3*Game.HEIGHT/4)/2)+Game.HEIGHT/4,Game.WIDTH/4,(3*Game.HEIGHT/4)/2);
//				Rectangle level5 = new Rectangle(Game.WIDTH/4,((3*Game.HEIGHT/4)/2)+Game.HEIGHT/4,Game.WIDTH/4,(3*Game.HEIGHT/4)/2);
//				Rectangle level6 = new Rectangle(Game.WIDTH/2,((3*Game.HEIGHT/4)/2)+Game.HEIGHT/4,Game.WIDTH/4,(3*Game.HEIGHT/4)/2);
//				Rectangle level7 = new Rectangle(Game.WIDTH / 2 + Game.WIDTH / 4,((3*Game.HEIGHT/4)/2)+Game.HEIGHT/4,Game.WIDTH/4,(3*Game.HEIGHT/4)/2);
				if(level0.intersects(mouse)){
					characterMenu.setSelectedLevel(0);
				}
				if(level1.intersects(mouse)){
					characterMenu.setSelectedLevel(1);
				}
				if(level2.intersects(mouse)){
					characterMenu.setSelectedLevel(2);
				}
//				if(level3.intersects(mouse)){
//					characterMenu.setSelectedLevel(3);
//				}
//				if(level4.intersects(mouse)){
//					characterMenu.setSelectedLevel(4);
//				}
//				if(level5.intersects(mouse)){
//					characterMenu.setSelectedLevel(5);
//				}
//				if(level6.intersects(mouse)){
//					characterMenu.setSelectedLevel(6);
//				}
//				if(level7.intersects(mouse)){
//					characterMenu.setSelectedLevel(7);
//				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
