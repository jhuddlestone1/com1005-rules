
/**
 * RunRuleNet.java
 * create a rule network & make deductions with it
 * @author Phil Green
 * This Version 23/1/2019
 */

import sheffield.*;
import java.util.*;
import pmatch.*;


public class RunRuleNet{
 public static void main(String[] arg) {
   // create object for output
   EasyWriter scr = new EasyWriter();
   

   // ----- PART 3 -----

   //make some rules
   
   //nephew
   ArrayList<String> nantes = new ArrayList<String>();
   nantes.add("?p sibling of ?sb");
   nantes.add("?p parent of ?c");
   RuleNode nrule = new RuleNode ("nephew rule", nantes, "?c nephew of ?sb");
   ArrayList<RuleNode> nsuccs = new ArrayList<RuleNode>();
   nrule.setSuccessors(nsuccs);
   
   //uncle
   ArrayList<String> uantes = new ArrayList<String>();
   uantes.add("?u brother of ?p");
   uantes.add("?p parent of ?c");
   RuleNode urule = new RuleNode ("uncle rule", uantes, "?u uncle of ?c");
   ArrayList<RuleNode> usuccs = new ArrayList<RuleNode>();
   usuccs.add(nrule);
   urule.setSuccessors(usuccs);
   
   //aunt
   ArrayList<String> aantes = new ArrayList<String>();
   aantes.add("?a sister of ?p");
   aantes.add("?p parent of ?c");
   RuleNode arule = new RuleNode ("aunt rule", aantes, "?a aunt of ?c");
   ArrayList<RuleNode> asuccs = new ArrayList<RuleNode>();
   asuccs.add(nrule);
   arule.setSuccessors(asuccs);
   
   //brother
   ArrayList<String> brantes = new ArrayList<String>();
   brantes.add("?b sibling of ?sb");
   brantes.add("?b father of ?c");
   RuleNode brrule = new RuleNode ("brother rule", brantes, "?b brother of ?sb");
   ArrayList<RuleNode> brsuccs = new ArrayList<RuleNode>();
   brsuccs.add(urule);
   brsuccs.add(arule);
   brrule.setSuccessors(brsuccs);
   
   //sister
   ArrayList<String> srantes = new ArrayList<String>();
   srantes.add("?s sibling of ?sb");
   srantes.add("?s mother of ?c");
   RuleNode srrule = new RuleNode ("sister rule", srantes, "?s sister of ?sb");
   ArrayList<RuleNode> srsuccs = new ArrayList<RuleNode>();
   srsuccs.add(urule);
   srsuccs.add(arule);
   srrule.setSuccessors(srsuccs);
   
   //sibling
   ArrayList<String> sbantes = new ArrayList<String>();
   sbantes.add("?p parent of ?sb1");
   sbantes.add("?p parent of ?sb2");
   RuleNode sbrule = new RuleNode ("sibling rule", sbantes, "?sb1 sibling of ?sb2");
   ArrayList<RuleNode> sbsuccs = new ArrayList<RuleNode>();
   sbsuccs.add(brrule);
   sbsuccs.add(srrule);
   sbrule.setSuccessors(sbsuccs);
   
   //grandfather
   ArrayList<String> gfantes= new ArrayList<String>();
   gfantes.add("?gf father of ?p");
   gfantes.add("?p parent of ?c");
   RuleNode gfrule = new RuleNode ("grandfather rule",gfantes, "?gf grandfather of ?c");
   ArrayList<RuleNode> gfsuccs = new ArrayList<RuleNode>();
   gfrule.setSuccessors(gfsuccs);
   
   //grandmother
   ArrayList<String> gmantes= new ArrayList<String>();
   gmantes.add("?gm mother of ?p");
   gmantes.add("?p parent of ?c");
   RuleNode gmrule = new RuleNode ("grandmother rule",gmantes, "?gm grandmother of ?c");
   ArrayList<RuleNode> gmsuccs = new ArrayList<RuleNode>();
   gmrule.setSuccessors(gmsuccs);
   
   //father-is-parent
   ArrayList<String> fpantes = new ArrayList<String>();
   fpantes.add("?f father of ?c");
   RuleNode fprule = new RuleNode ("father-is-parent rule", fpantes, "?f parent of ?c");
   ArrayList<RuleNode> fpsuccs = new ArrayList<RuleNode>();
   fpsuccs.add(gfrule);
   fpsuccs.add(gmrule);
   fpsuccs.add(sbrule);
   fpsuccs.add(urule);
   fpsuccs.add(arule);
   fprule.setSuccessors(fpsuccs);
   
   //mother-is-parent
   ArrayList<String> mpantes = new ArrayList<String>();
   mpantes.add("?m mother of ?c");
   RuleNode mprule = new RuleNode ("mother-is-parent rule", mpantes, "?m parent of ?c");
   ArrayList<RuleNode> mpsuccs = new ArrayList<RuleNode>();
   mpsuccs.add(gfrule);
   mpsuccs.add(gmrule);
   mpsuccs.add(sbrule);
   mpsuccs.add(urule);
   mpsuccs.add(arule);
   mprule.setSuccessors(mpsuccs);

   
   // the set of rulenodes
   ArrayList<RuleNode> rset = new ArrayList<RuleNode>();
   rset.add(nrule);
   rset.add(urule);
   rset.add(arule);
   rset.add(brrule);
   rset.add(srrule);
   rset.add(sbrule);
   rset.add(gfrule);
   rset.add(gmrule);
   rset.add(fprule);
   rset.add(mprule);
   
   // make the rule net
   RuleNet rs = new RuleNet(rset);
   //initialise it - set up initial tokens
   rs.initialise(); 
   
   //add facts
   ArrayList<String> facts = new ArrayList<String>();
   rs.addFact("H7 father of H8");
   rs.addFact("H8 father of E");
   
   // ----- PART 4 -----
   rs.addFact("Jill mother of David");
   rs.addFact("Jill mother of Shula");
   rs.addFact("David father of Pip");
   rs.addFact("Shula mother of Daniel");
   
  }
}