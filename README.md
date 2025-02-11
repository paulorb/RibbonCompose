# RibbonCompose



A group is composed of many items

```
RibbonTab(name="tab1", label="Tab 1", scalingPolicy= 
  ScalingPolicy(
    idealSizes= {},    // TO BE DEFINED
    resizingOrder= {}   // TO BE DEFINED
  )
) {
  RibbonGroup(name="group1") {
     RibbonButton(label="button1", icon="test.png")
  }
  RibbonGroup(name="group2") {
     RibbonButton(label="button2", icon="test.png")
     RibbonButton(label="button3", icon="test.png")
  }
  RibbonGroup(name="group3") {
     RibbonButton(label="button4", icon="test.png")
     RibbonButton(label="button5", icon="test.png")
     RibbonButton(label="button6", icon="test.png")
  }
  RibbonGroup(name="group4") {
     RibbonButton(label="button7", icon="test.png")
     RibbonButton(label="button8", icon="test.png")
     RibbonToggleButton(label="button9", icon="test.png")
     RibbonButton(label="button10", icon="test.png")
     RibbonToggleButton(label="button11", icon="test.png")
  }
}

```

Define order of scaling