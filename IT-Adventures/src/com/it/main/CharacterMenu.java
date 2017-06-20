package com.it.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class CharacterMenu {

	private BufferedImage image;
	private CharacterType selectedCharacter;
	private int selectedLevel;
	private Game game;

	public CharacterMenu(BufferedImageLoader imageLoader,Game game) {
		this.game=game;
		image = imageLoader.loadImage("/images/characterMenu.png");
	}

	public void tick() {

	}

	public void render(Graphics g) {
		g.drawImage(image, 0, 0, Game.WIDTH, Game.HEIGHT, null);
		try {
			switch (selectedCharacter) {

			case Jaime:
				g.setColor(Color.RED);
				g.drawRect(0, 0, Game.WIDTH / 4, Game.HEIGHT / 4);
				break;
			}
		} catch (NullPointerException e) {

		}
	}

	public CharacterType getSelectedCharacter() {
		return selectedCharacter;
	}

	public void setSelectedCharacter(CharacterType selectedCharacter) {
		this.selectedCharacter = selectedCharacter;
	}

	public int getSelectedLevel() {
		return selectedLevel;
	}

	public void setSelectedLevel(int selectedLevel) {
		this.selectedLevel = selectedLevel;
		game.setCharacter(selectedCharacter);
		game.setLevelNumber(selectedLevel);
		game.loadNewLevel();
		Game.State=Game.STATE.Game;
	}

}
