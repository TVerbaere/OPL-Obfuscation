package com.tibo.processors;

import spoon.Launcher;

public class ObfuscatorLauncher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String projectdirectory = "../AppliTest/src/";
		String outputdirectory = "../Spooned/";
		final String[] arguments = {
				"-i", projectdirectory,
				"-o", outputdirectory
		};
		
		final Launcher launcher = new Launcher();
		launcher.setArgs(arguments);
				
		launcher.addProcessor(new ClassChangeProcessor());
		launcher.addProcessor(new VariableChangeProcessor());
		launcher.addProcessor(new MethodChangeProcessor());
		launcher.addProcessor(new RefractorProcessor());
		launcher.addProcessor(new DocChangeProcessor());
		launcher.addProcessor(new StringEncoderProcessor());
		
		launcher.run();

	}

}
