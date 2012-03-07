//
// Triple Play - utilities for use in PlayN-based games
// Copyright (c) 2011, Three Rings Design, Inc. - All rights reserved.
// http://github.com/threerings/tripleplay/blob/master/LICENSE

package tripleplay.ui.bgs;

import pythagoras.f.FloatMath;
import pythagoras.f.IDimension;

import playn.core.ImmediateLayer;
import playn.core.PlayN;
import playn.core.Surface;

import tripleplay.ui.Background;

/**
 * A background that displays a bevel around a solid color.
 */
public class BeveledBackground extends Background
{
    /**
     * Creates a beveled background with the specified colors and insets.
     */
    public BeveledBackground (int bgColor, int ulColor, int brColor,
                              float top, float right, float bottom, float left) {
        super(top, right, bottom, left);
        _bgColor = bgColor;
        _ulColor = ulColor;
        _brColor = brColor;
    }

    @Override protected Instance instantiate (final IDimension size) {
        return new LayerInstance(PlayN.graphics().createImmediateLayer(new ImmediateLayer.Renderer() {
            public void render (Surface surf) {
                float width = size.width(), height = size.height();
                float bot = height-1, right=width-1;
                surf.setFillColor(_bgColor).fillRect(0, 0, width, height);
                surf.setFillColor(_ulColor).
                    drawLine(0, 0, right, 0, 2).drawLine(0, 0, 0, bot, 2);
                surf.setFillColor(_brColor).
                    drawLine(right, 0, right, bot, 1).drawLine(1, bot-1, right-1, bot-1, 1).
                    drawLine(0, bot, right, bot, 1).drawLine(right-1, 1, right-1, bot-1, 1);
            }
        }));
    }

    protected int _bgColor, _ulColor, _brColor;
}