package com.threerings.ezgame.client;

import java.awt.Component;
import java.awt.Container;

import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;

import java.util.WeakHashMap;

import javax.swing.JPanel;

import com.samskivert.swing.VGroupLayout;
import com.samskivert.swing.util.SwingUtil;

import com.threerings.crowd.client.PlaceView;
import com.threerings.crowd.data.PlaceObject;
import com.threerings.crowd.util.CrowdContext;

import com.threerings.ezgame.data.EZGameConfig;
import com.threerings.ezgame.data.EZGameObject;

import com.threerings.ezgame.Game;

public class EZGamePanel extends JPanel
    implements ContainerListener, PlaceView
{
    public EZGamePanel (CrowdContext ctx, EZGameController ctrl)
    {
        super(new VGroupLayout(VGroupLayout.STRETCH));

        _ctx = ctx;
        _ctrl = ctrl;

        // add a listener so that we hear about all new children
        addContainerListener(this);

        EZGameConfig cfg = (EZGameConfig) ctrl.getPlaceConfig();
        try {
            // TODO
            _gameView = (Component) Class.forName(cfg.configData).newInstance();
            add(_gameView);
        } catch (RuntimeException re) {
            throw re;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // TODO: Add a standard chat display?
        //addChild(new ChatDisplayBox(ctx));
    }

    // from PlaceView
    public void willEnterPlace (final PlaceObject plobj)
    {
        // don't start notifying anything of the game until we've
        // notified the game manager that we're in the game
        // (done in GameController, and it uses callLater, so we do it twice!)
        _ctx.getClient().getRunQueue().postRunnable(new Runnable() {
            public void run () {
                _ctx.getClient().getRunQueue().postRunnable(new Runnable() {
                    public void run () {
                        _ezObj = (EZGameObject) plobj;
                        notifyOfGame(_gameView);
                    }
                });
            }
        });
    }

    // from PlaceView
    public void didLeavePlace (PlaceObject plobj)
    {
        removeListeners(_gameView);
        _ezObj = null;
    }

    // from ContainerListener
    public void componentAdded (ContainerEvent event)
    {
        if (_ezObj != null) {
            notifyOfGame(event.getChild());
        }
    }

    // from ContainerListener
    public void componentRemoved (ContainerEvent event)
    {
        if (_ezObj != null) {
            removeListeners(event.getChild());
        }
    }

    /**
     * Find any children of the specified object that implement
     * com.metasoy.game.Game and provide them with the GameObject.
     */
    protected void notifyOfGame (Component root)
    {
        SwingUtil.applyToHierarchy(root, new SwingUtil.ComponentOp() {
            public void apply (Component comp) {
                if (comp instanceof Game) {
                    // only notify the Game if we haven't seen it before
                    if (null == _seenGames.put(comp, Boolean.TRUE)) {
                        ((Game) comp).setGameObject(_ctrl.gameObjImpl);
                    }
                }
                // always check to see if it's a listener
                _ctrl.gameObjImpl.registerListener(comp);
            }
        });
    }

    protected void removeListeners (Component root)
    {
        SwingUtil.applyToHierarchy(root, new SwingUtil.ComponentOp() {
            public void apply (Component comp) {
                _ctrl.gameObjImpl.unregisterListener(comp);
            }
        });
    }

    protected CrowdContext _ctx;
    protected EZGameController _ctrl;

    protected Component _gameView;

    /** A weak-key hash of the Game interfaces we've already seen. */
    protected WeakHashMap<Component, Boolean> _seenGames =
        new WeakHashMap<Component, Boolean>();

    protected EZGameObject _ezObj;
}