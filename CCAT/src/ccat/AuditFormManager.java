package ccat;


import java.awt.Dimension;
import java.awt.TextField;
import javafx.scene.control.RadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author JRebo_000
 */
public class AuditFormManager {

        private final JPanel [] panel;
        private final ButtonGroup [] contexts;
        private TextField [] textViews;
        private final String [] checklist= {"1  Admission Checklist", 
                                            "2  Decision for Transfer entered",
                                            "3  Quick View completed",
                                            "4  Daily Weight - Fluid Balance (CCU ONLY)",
                                            "1  Vital Signs validatded using forms only",
                                            "2  Oral Care q2h",
                                            "3  Restraints checked as per policy",
                                            "4  Patient Positioning q2h",
                                            "5  Pressure Relief Strategies completed",
                                            "6  Workload Parameters completed",
                                            "1  Message Ball is current",
                                            "2  Infusion Location documented (NOT venuous/arterial",
                                            "3  Infusions stopped at discharge time",
                                            "1  Lines, Tubes, Drains are current and accurate",
                                            "2  Insertion Sites are corrected",
                                            "3  Proccess Lines stopped at discharge",
                                            "4  Line Assessments completed q4h",
                                            "1  Assessments Documented q4h",
                                            "2  Safety Plus form completed q shift",
                                            "3  Braden Scale completed",
                                            "1  Resuscitation 24 Hours Prior to Admission entered",
                                            "2  Continuous Events current",
                                            "3  Transport Lines current",
                                            "4  Other events used appropriately",
                                            "5  Rounds documented (Day shift only)",
                                            "1  Patient assigned to appropriate service",
                                            "2  ICDSC completed (q0600h)(ICU ONLY)",
                                            "1  Medications charted in appropriate sections (PRN vs Scheduled)",
                                            "2  MAR has two sets of initials",
                                            "3  New/changed orders have two sets of initials",
                                            "4  Documentations of changed orders done as per policy",
                                            "5  Indepentdent double check of initial programming done",
                                            "6  Independent double check of medicated infusions at shift change",
                                            "1  Allergy form completed",
                                            "2  Actual Weight completed",
                                            "3  Goals of Care completed and apropriate",
                                            "1  Falls Risk Assessment form completed",
                                            "2  Belite Functional Assessment form completed",
                                            "3  TAPS used",
                                            "1  Medicus reviewed q8h over past 24 hours",
                                            "2  Initials present: ",
                                            "1  Appropriate logo displayed in room",
                                            "2  Appropriate sticker on patient ID band",
                                            "1  Appropriate Isolation sign on door"};
        private final int MAXLEN = 44;
        
        public AuditFormManager(){
            panel = new JPanel[MAXLEN];
            contexts = new ButtonGroup[MAXLEN];
            JRadioButton yes;
            JRadioButton no;
            JRadioButton na;
            
            for (int i = 0; i < MAXLEN; i++){
                panel[i].setPreferredSize(new Dimension(745, 40));
                textViews[i] = new TextField(checklist[i]);            
                panel[i].add(textViews[i]);
                yes = new JRadioButton("Yes");
                no = new JRadioButton("No");
                na = new JRadioButton("n/a");
                contexts[i].add(yes);
                contexts[i].add(no);
                contexts[i].add(na);
                panel[i].add(yes);
                panel[i].add(yes);
                panel[i].add(yes);
            }
        }
        
        public void populateScrollPane(JPanel jpanel){
            for (int i = 0; i < MAXLEN; i++){
                jpanel.add(panel[i]);
            }
        }
}
