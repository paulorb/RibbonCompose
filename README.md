# RibbonCompose

## Algorithm
1 - Build all elements with its ideal size
```text
[RibbonGroup 1] [RibbonGroup 2][RibbonGroup 3] [RibbonGroup 4]
  ideal size      ideal size      ideal size     ideal size
```

2 - Measure all of them
```text
[RibbonGroup 1] [RibbonGroup 2][RibbonGroup 3] [RibbonGroup 4]
  ideal size      ideal size      ideal size     ideal size
  100px x 50px    100px x 50px     100px x 50px    100px x 50px
```

3 - If fits place all of them - END

4 - If not, then verify the resizing order, get the first element of the resize order (stack)
and build it again now using its resized order and measure only this newly created compoenent

Pop the first element out of the stack
```text
   1 -  RibbonGroup 2 -> Medium
   2 -  RibbonGroup 1 -> Small
   3 -  RibbonGroup 3 -> Popup
   4 -  RibbonGroup 4 -> Small
```

If there is no more elements on the stack go to step 6

Remove the element that does not fit (RibbonGroup2) and replace it the new element created
```text
[RibbonGroup 1]                   [RibbonGroup 3] [RibbonGroup 4]
  ideal size                       ideal size     ideal size
  100px x 50px                     100px x 50px    100px x 50px
  
                Build again
                [RibbonGroup 2]
                Scaled medium
                50px x 50px
```

Measure the total elements size again (of course not using compose, but the list of measurements sizes stored in memory)


5 - Go to step 3

(FUTURE)
6 - Measure all elements (using the list of measurements) 
verify how many can fit, place only the ones that can fit 
also place an button with an icon > for displaying the other missing components





Idea 1 
One composable function that receives the subcomponenets as arguments (not as composable functions)

```
@Composable
RibbonTabBody(
    RibbonComponents.build()
        .ribbonGroup(
            name="group1",
            idealSize=RibbonComponentSize.Medium,
            scaleOrder=2,
            scaleSize=RibbonComponentSize.Small
            RibbonButtons.build()
             .ribbonButton("Button1", Res.drawable.note)
             .ribbonButton("Button2", Res.drawable.note)
        )
        .ribbonGroup(
            name="group2",
            idealSize=RibbonComponentSize.Medium,
            scaleOrder=1,
            scaleSize=RibbonComponentSize.Small
            RibbonButtons.build()
             .ribbonButton("Button3", Res.drawable.note)
             .ribbonButton("Button4", Res.drawable.note)
        )
        
)

```


Idea 2 - Not sure if this is works
```
@Composable
RibbonTab(name="tab1", label="Tab 1", scalingPolicy= 
  ScalingPolicy(
    idealSizes= {},    // TO BE DEFINED
    resizingOrder= {}   // TO BE DEFINED
  )
) {
  RibbonGroup(name="group1") {  //@Composable function
     RibbonButton(label="button1", icon="test.png")  //@Composable function
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


