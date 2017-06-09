package com.example.aghil.tobeortohave.model;


import com.example.aghil.tobeortohave.R;
import com.example.aghil.tobeortohave.fragment.BarGraphFragment;
import com.example.aghil.tobeortohave.fragment.ContactFragment;
import com.example.aghil.tobeortohave.fragment.EventFragment;
import com.example.aghil.tobeortohave.fragment.FragmentInterface;
import com.example.aghil.tobeortohave.fragment.MapFragment;
import com.example.aghil.tobeortohave.fragment.NotifFragment;
import com.example.aghil.tobeortohave.fragment.ProgressBarFragment;

/**
 * Created by aghil on 26/04/2017.
 */

public enum Item {
    EVENT(R.drawable.calendar,"events",new EventFragment()),
    HORAIRE(R.drawable.horaire,"Flux",new BarGraphFragment()),
    LIV(R.drawable.truck,"Livraison",new ProgressBarFragment()),
    NOTIF(R.drawable.cloche,"Notifications",new NotifFragment()),
    CONTACT(R.drawable.message,"Contacter le responsable",new ContactFragment()),
    MAP(R.drawable.events,"Voir la localisation des evenements",new MapFragment());

    private String content;
    private String detail;
    private int id;
    private FragmentInterface fragment;
    private Item(int id, String content, FragmentInterface fragment){
        this.id = id;
        this.content = content;
        this.fragment = fragment;

    }

    public String getContent() {
        return content;
    }

    public String getDetail() {
        return detail;
    }

    public int getId() {
        return id;
    }
    public FragmentInterface getFragment(){
        return fragment;
    }
}
