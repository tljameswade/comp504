/**
 * The controller for music player
 */
package controller;

import java.awt.EventQueue;

import model.IViewAdapter;
import model.MusicPlayerModel;
import provided.util.ABCInstrument;
import provided.util.ABCUtil;
import view.IModelAdapter;
import view.MusicPlayerFrame;

/**
 * Controller for music player
 * @author Qianyi Wu, Suozhi Qi
 * @version 1.0
 */
public class MusicPlayerController {

	private MusicPlayerModel model;
	private MusicPlayerFrame<ABCInstrument> view;

	/**
	 * Music Player constructor
	 */
	public MusicPlayerController() {

		model = new MusicPlayerModel(new IViewAdapter() {

			@Override
			public void DisplayFileContent(String content) {
				// TODO Auto-generated method stub
				view.getTxtpnFileContent().setText(content);
			}

			@Override
			public void addInstrument(ABCInstrument instrument) {
				view.addInstrument(instrument);
			}

		});

		view = new MusicPlayerFrame<ABCInstrument>(new IModelAdapter<ABCInstrument>() {

			@Override
			public void Open(String filename) {
				// TODO Auto-generated method stub
				model.LoadFile(filename);

			}

			@Override
			public void Parse() {
				// TODO Auto-generated method stub
				view.getTxtpnParsedFile().setText(model.getParsed());
			}

			@Override
			public void Play(ABCInstrument instrument) {
				model.Play(instrument);

			}

			@Override
			public void Stop() {
				model.stop();
			}
		});

	}

	/**
	 * 
	 */
	public void start() {
		ABCInstrument[] instruments = ABCUtil.Singleton.getInstruments();
		for (ABCInstrument instrument : instruments) {
			view.addInstrument(instrument);
		}
		model.start();
		view.start();
	}

	/**
	 * @param args the main method
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() { // Java specs say that the system must be constructed on the GUI event thread.
			public void run() {
				try {
					MusicPlayerController controller = new MusicPlayerController(); // instantiate the system
					controller.start(); // start the system
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
