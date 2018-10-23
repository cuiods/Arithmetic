package com.cuiods.arithmetic.seam.model;

import java.awt.image.BufferedImage;

public class ImageModel {

    private SeamCarving seamCarving = new SeamCarving();

    public BufferedImage seamImage(BufferedImage image, EnergyMethod method) {
        int[][] data = new int[image.getHeight()][image.getWidth()];
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                data[i][j] = image.getRGB(j,i);
            }
        }
        seamCarving.setPicture(data);
        int[][] result = seamCarving.seamCarving(method);
        BufferedImage imageResult = new BufferedImage(result[0].length, result.length, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                imageResult.setRGB(j,i,result[i][j]);
            }
        }
        return imageResult;
    }

}
