package com.cuiods.arithmetic.seam.model;

public class SeamCarving {

    private int[][] picture = null;
    private int[][] energy = null;
    private int[] tags = null;
    private int height = 0;
    private int width = 0;

    public SeamCarving() { }

    public SeamCarving(int[][] picture) {
        setPicture(picture);
    }

    public int[][] seamCarving() {
        return seamCarving(width/2, height/2);
    }

    public int[][] seamCarving(int hNum, int vNum) {
        if (picture.length == 0) return picture;
        if (picture.length < 2 && picture[0].length < 2) return picture;
        if (hNum < picture[0].length) {
            for (int i = 0; i < hNum; i++) {
                horizonSeamCarving();
            }
        }
        if (vNum < picture.length) {
            for (int i = 0; i < vNum; i++) {
                verticalSeamCarving();
            }
        }
        return getPicture();
    }

    public int[][] getPicture() {
        if (tags != null) {
            int[][] result = new int[height][width];
            for (int i = 0; i < height; i++) {
                if (width >= 0) System.arraycopy(picture[i], 0, result[i], 0, width);
            }
            return result;
        }
        return picture;
    }

    public void setPicture(int[][] picture) {
        assert picture.length>0;
        this.picture = picture;
        this.energy = null;
        this.tags = null;
        height = picture.length;
        width = picture[0].length;
    }

    private void horizonSeamCarving() {
        calculateEnergy();
        int[][] globalTags = new int[height][width];
        tags = new int[height];
        int[] lastSeam = new int[width];
        for (int i = 0; i < width; i++) {
            lastSeam[i] = energy[0][i];
            globalTags[0][i] = 0;
        }
        for (int i = 1; i < height; i++) {
            int[] currentSeam = new int[width];
            for (int j = 0; j < width; j++) {
                currentSeam[j] = energy[i][j];
                if (j-1 < 0) {
                    if (lastSeam[j]<=lastSeam[j+1]) {
                        currentSeam[j] += lastSeam[j];
                        globalTags[i][j] = 0;
                    } else {
                        currentSeam[j] += lastSeam[j+1];
                        globalTags[i][j] = 1;
                    }
                } else if (j+1 > width-1) {
                    if (lastSeam[j]<=lastSeam[j-1]) {
                        currentSeam[j] += lastSeam[j];
                        globalTags[i][j] = 0;
                    } else {
                        currentSeam[j] += lastSeam[j-1];
                        globalTags[i][j] = -1;
                    }
                } else {
                    if (lastSeam[j-1] < lastSeam[j]) {
                        currentSeam[j] += lastSeam[j-1];
                        globalTags[i][j] = -1;
                    } else if (lastSeam[j+1] < lastSeam[j]) {
                        currentSeam[j] += lastSeam[j+1];
                        globalTags[i][j] = 1;
                    } else {
                        currentSeam[j] += lastSeam[j];
                        globalTags[i][j] = 0;
                    }
                }
            }
            lastSeam = currentSeam;
        }
        int minIndex = 0;
        int minValue = Integer.MAX_VALUE;
        for (int i = 0; i < width; i++) {
            if (lastSeam[i] < minValue) {
                minValue = lastSeam[i];
                minIndex = i;
            }
        }
        for (int i = height-1; i>=0; i--) {
            tags[i] = minIndex;
            minIndex = minIndex + globalTags[i][minIndex];
        }
        for (int i = 0; i < height; i++) {
            if (width - 1 - tags[i] >= 0)
                System.arraycopy(picture[i], tags[i] + 1, picture[i], tags[i], width - 1 - tags[i]);
        }
        width = width-1;
    }

    private void verticalSeamCarving() {
        calculateEnergy();
        int[][] globalTags = new int[height][width];
        tags = new int[width];
        int[] lastSeam = new int[height];
        for (int i = 0; i < height; i++) {
            lastSeam[i] = energy[i][0];
            globalTags[i][0] = 0;
        }
        for (int i = 1; i < width; i++) {
            int[] currentSeam = new int[height];
            for (int j = 0; j < height; j++) {
                currentSeam[j] = energy[j][i];
                if (j-1 < 0) {
                    if (lastSeam[j]<=lastSeam[j+1]) {
                        currentSeam[j] += lastSeam[j];
                        globalTags[j][i] = 0;
                    } else {
                        currentSeam[j] += lastSeam[j+1];
                        globalTags[j][i] = 1;
                    }
                } else if (j+1 > height-1) {
                    if (lastSeam[j]<=lastSeam[j-1]) {
                        currentSeam[j] += lastSeam[j];
                        globalTags[j][i] = 0;
                    } else {
                        currentSeam[j] += lastSeam[j-1];
                        globalTags[j][i] = -1;
                    }
                } else {
                    if (lastSeam[j-1] < lastSeam[j]) {
                        currentSeam[j] += lastSeam[j-1];
                        globalTags[j][i] = -1;
                    } else if (lastSeam[j+1] < lastSeam[j]) {
                        currentSeam[j] += lastSeam[j+1];
                        globalTags[j][i] = 1;
                    } else {
                        currentSeam[j] += lastSeam[j];
                        globalTags[j][i] = 0;
                    }
                }
            }
            lastSeam = currentSeam;
        }
        int minIndex = 0;
        int minValue = Integer.MAX_VALUE;
        for (int i = 0; i < height; i++) {
            if (lastSeam[i] < minValue) {
                minValue = lastSeam[i];
                minIndex = i;
            }
        }
        for (int i = width-1; i>=0; i--) {
            tags[i] = minIndex;
            minIndex = minIndex + globalTags[minIndex][i];
        }
        for (int i = 0; i < width; i++) {
            for (int j = tags[i]; j < height-1; j++) {
                picture[j][i] = picture[j+1][i];
            }
        }
        height = height-1;
    }

    private void calculateEnergy() {
        energy = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                energy[i][j] = energy(i,j);
            }
        }
    }

    private int energy(int x, int y) {
        int leftRGB = picture[(x-1+ height)% height][y];
        int rightRGB = picture[(x+1)% height][y];
        int upperRGB = picture[x][(y+1)% width];
        int downRGB = picture[x][(y-1+ width)% width];
        return (int)(Math.pow(leftRGB&0xff-rightRGB&0xff,2)+Math.pow((leftRGB>>8)&0xff-(rightRGB>>8)&0xff,2)+Math.pow((leftRGB>>16)&0xff-(rightRGB>>16)&0xff,2)
                +Math.pow(upperRGB&0xff-downRGB&0xff,2)+Math.pow((upperRGB>>8)&0xff-(downRGB>>8)&0xff,2)+Math.pow((upperRGB>>16)&0xff-(downRGB>>16)&0xff,2));
    }
}
