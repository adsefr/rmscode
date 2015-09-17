package com.rms.base.gen.javaclass.generator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.rms.base.exception.NotFoundException;
import com.rms.base.gen.javaclass.model.PackageInfo;
import com.rms.base.logging.Logger;
import com.rms.base.validate.Assertion;

public class PackageGenerator extends BaseGenerator {

	private final Logger logger = Logger.getLogger(PackageGenerator.class);

	private PackageInfo packageInfo;

	private AnnotationGnerator annotationGnerator = new AnnotationGnerator();

	/**
	 *
	 */
	public PackageGenerator() {

		super();
	}

	/**
	 * @param packageInfo
	 */
	public PackageGenerator(PackageInfo packageInfo) {

		super();

		this.packageInfo = packageInfo;
	}

	/**
	 * @return packageInfo
	 */
	public PackageInfo getPackageInfo() {

		return packageInfo;
	}

	/**
	 * @param packageInfo
	 *            セットする packageInfo
	 */
	public void setPackageInfo(PackageInfo packageInfo) {

		this.packageInfo = packageInfo;
	}

	@Override
	public void generate() {

		clearBuffer();

		append("package ");
		append(packageInfo.getPackageName());
		append(";");
		append(lineSeparator);
		append(lineSeparator);
	}

	/**
	 *
	 * @param targetPath
	 * @throws IOException
	 */
	public void generatePackageInfoFile(Path targetPath) throws IOException {

		Assertion.assertDirectory("targetPath", targetPath.toFile());// TODO

		clearBuffer();
		// try {
		// Path targetDirectory = createDestDirectory();
		// Path packageInfoFile = Paths.get(targetDirectory.toString(),
		// "package-info.java");
		// Files.createFile(packageInfoFile);
		//
		// buffered.append(packageInfo.getComment());
		// buffered.append(packageInfo.getLineSeparator());
		// buffered.append(packageInfo.getAnnotationInfo().toString());// TODO
		// buffered.append(packageInfo.getLineSeparator());
		// buffered.append("package");
		// buffered.append(" ");
		// buffered.append(packageInfo.getPackageName());
		// buffered.append(Characters.SEMICOLON);
		// BufferedWriter bufferedWriter =
		// IOFactory.newBufferedWriter(packageInfoFile.toFile(),
		// Encodes.CHARSET_UTF8);
		// bufferedWriter.write(buffered.toString());
		// bufferedWriter.close();// TODO
		// } catch (IOException e) {
		// logger.error(e);
		// }

		append("");

		// IOUtil.write(targetPath.toFile(), getBuffer().toString());
	}

	private Path createDestDirectory() throws IOException {

		Path destDirectory = packageInfo.getDestDirecotry();

		if (Files.isDirectory(destDirectory)) {
			throw new NotFoundException("directory is not found.[" + destDirectory.getFileName() + "]");
		}

		String packageName = packageInfo.getPackageName();

		String destPath = destDirectory.toString();

		Path targetDirectory = Paths.get(destPath, packageName.split("."));

		return Files.createDirectories(targetDirectory);
	}
}
