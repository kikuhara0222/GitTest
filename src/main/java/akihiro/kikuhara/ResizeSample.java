package akihiro.kikuhara;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import javax.imageio.ImageIO;

public class ResizeSample {
	/**
	 * 画像リサイズするメソッド
	 * 
	 * @param inputPath
	 *            入力パス
	 * @param outputPath
	 *            出力パス
	 * @param maxWidth
	 *            指定幅
	 * @param maxHeight
	 *            指定高さ
	 * @throws IOException
	 */
	public void resizeByScaledInstance(BufferedImage bufImg, String outputPath, int maxWidth, int maxHeight)
			throws IOException {

		// 元画像の読み込み
		BufferedImage sourceImage = bufImg;

		// 縮小比率の計算
		BigDecimal bdW = new BigDecimal(maxWidth);
		bdW = bdW.divide(new BigDecimal(sourceImage.getWidth()), 8, BigDecimal.ROUND_HALF_UP);
		BigDecimal bdH = new BigDecimal(maxHeight);
		bdH = bdH.divide(new BigDecimal(sourceImage.getHeight()), 8, BigDecimal.ROUND_HALF_UP);
		// 縦横比は変えずに最大幅、最大高さを超えないように比率を指定する
		if (bdH.compareTo(bdW) < 0) {
			maxWidth = -1;
		} else {
			maxHeight = -1;
		}

		// スケールは以下から選択
		// Image.SCALE_AREA_AVERAGING 遅いがなめらか
		// Image.SCALE_DEFAULT 普通 速度はFASTと変わらない
		// Image.SCALE_FAST 早くて汚い
		// Image.SCALE_REPLICATE わからん そこそこ汚い
		// Image.SCALE_SMOOTH 遅くてなめらか
		Image targetImage = sourceImage.getScaledInstance(maxWidth, maxHeight, Image.SCALE_AREA_AVERAGING);

		// Image -> BufferedImageの変換
		BufferedImage targetBufferedImage = new BufferedImage(targetImage.getWidth(null), targetImage.getHeight(null),
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = targetBufferedImage.createGraphics();
		g.drawImage(targetImage, 0, 0, null);

		// 変換画像の出力
		ImageIO.write(targetBufferedImage, "JPG" , new File(outputPath));
	}
}
