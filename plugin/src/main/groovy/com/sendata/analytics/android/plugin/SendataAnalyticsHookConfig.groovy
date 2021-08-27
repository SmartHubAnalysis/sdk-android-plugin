/*
 * Created by wangzhuozhou on 2015/08/12.
 * Copyright 2015－2021 Sensors Data Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sendata.analytics.android.plugin

import org.objectweb.asm.Opcodes

class SendataAnalyticsHookConfig {
    public static final String SENDATA_ANALYTICS_API = "com/sendata/analytics/android/sdk/SendataAutoTrackHelper"
    public final static HashMap<String, SendataAnalyticsMethodCell> INTERFACE_METHODS = new HashMap<>()
    public final static HashMap<String, SendataAnalyticsMethodCell> CLASS_METHODS = new HashMap<>()

    static {
        addInterfaceMethod(new SendataAnalyticsMethodCell(
                'onCheckedChanged',
                '(Landroid/widget/CompoundButton;Z)V',
                'android/widget/CompoundButton$OnCheckedChangeListener',
                'trackViewOnClick',
                '(Landroid/view/View;)V',
                1, 1,
                [Opcodes.ALOAD]))
        addInterfaceMethod(new SendataAnalyticsMethodCell(
                'onRatingChanged',
                '(Landroid/widget/RatingBar;FZ)V',
                'android/widget/RatingBar$OnRatingBarChangeListener',
                'trackViewOnClick',
                '(Landroid/view/View;)V',
                1, 1,
                [Opcodes.ALOAD]))
        addInterfaceMethod(new SendataAnalyticsMethodCell(
                'onStopTrackingTouch',
                '(Landroid/widget/SeekBar;)V',
                'android/widget/SeekBar$OnSeekBarChangeListener',
                'trackViewOnClick',
                '(Landroid/view/View;)V',
                1, 1,
                [Opcodes.ALOAD]))
        addInterfaceMethod(new SendataAnalyticsMethodCell(
                'onCheckedChanged',
                '(Landroid/widget/RadioGroup;I)V',
                'android/widget/RadioGroup$OnCheckedChangeListener',
                'trackRadioGroup',
                '(Landroid/widget/RadioGroup;I)V',
                1, 2,
                [Opcodes.ALOAD, Opcodes.ILOAD]))
        addInterfaceMethod(new SendataAnalyticsMethodCell(
                'onClick',
                '(Landroid/content/DialogInterface;I)V',
                'android/content/DialogInterface$OnClickListener',
                'trackDialog',
                '(Landroid/content/DialogInterface;I)V',
                1, 2,
                [Opcodes.ALOAD, Opcodes.ILOAD]))
        addInterfaceMethod(new SendataAnalyticsMethodCell(
                'onItemSelected',
                '(Landroid/widget/AdapterView;Landroid/view/View;IJ)V',
                'android/widget/AdapterView$OnItemSelectedListener',
                'trackListView',
                '(Landroid/widget/AdapterView;Landroid/view/View;I)V',
                1, 3,
                [Opcodes.ALOAD, Opcodes.ALOAD, Opcodes.ILOAD]))
        addInterfaceMethod(new SendataAnalyticsMethodCell(
                'onGroupClick',
                '(Landroid/widget/ExpandableListView;Landroid/view/View;IJ)Z',
                'android/widget/ExpandableListView$OnGroupClickListener',
                'trackExpandableListViewOnGroupClick',
                '(Landroid/widget/ExpandableListView;Landroid/view/View;I)V',
                1, 3,
                [Opcodes.ALOAD, Opcodes.ALOAD, Opcodes.ILOAD]))
        addInterfaceMethod(new SendataAnalyticsMethodCell(
                'onChildClick',
                '(Landroid/widget/ExpandableListView;Landroid/view/View;IIJ)Z',
                'android/widget/ExpandableListView$OnChildClickListener',
                'trackExpandableListViewOnChildClick',
                '(Landroid/widget/ExpandableListView;Landroid/view/View;II)V',
                1, 4,
                [Opcodes.ALOAD, Opcodes.ALOAD, Opcodes.ILOAD, Opcodes.ILOAD]))
        addInterfaceMethod(new SendataAnalyticsMethodCell(
                'onTabChanged',
                '(Ljava/lang/String;)V',
                'android/widget/TabHost$OnTabChangeListener',
                'trackTabHost',
                '(Ljava/lang/String;)V',
                1, 1,
                [Opcodes.ALOAD]))
        addInterfaceMethod(new SendataAnalyticsMethodCell(
                'onTabSelected',
                '(Landroid/support/design/widget/TabLayout$Tab;)V',
                'android/support/design/widget/TabLayout$OnTabSelectedListener',
                'trackTabLayoutSelected',
                '(Ljava/lang/Object;Ljava/lang/Object;)V',
                0, 2,
                [Opcodes.ALOAD, Opcodes.ALOAD]))
        addInterfaceMethod(new SendataAnalyticsMethodCell(
                'onTabSelected',
                '(Lcom/google/android/material/tabs/TabLayout$Tab;)V',
                'com/google/android/material/tabs/TabLayout$OnTabSelectedListener',
                'trackTabLayoutSelected',
                '(Ljava/lang/Object;Ljava/lang/Object;)V',
                0, 2,
                [Opcodes.ALOAD, Opcodes.ALOAD]))
        addInterfaceMethod(new SendataAnalyticsMethodCell(
                'onMenuItemClick',
                '(Landroid/view/MenuItem;)Z',
                'android/widget/Toolbar$OnMenuItemClickListener',
                'trackMenuItem',
                '(Landroid/view/MenuItem;)V',
                1, 1,
                [Opcodes.ALOAD]))
        addInterfaceMethod(new SendataAnalyticsMethodCell(
                'onMenuItemClick',
                '(Landroid/view/MenuItem;)Z',
                'android/support/v7/widget/Toolbar$OnMenuItemClickListener',
                'trackMenuItem',
                '(Landroid/view/MenuItem;)V',
                1, 1,
                [Opcodes.ALOAD]))
        addInterfaceMethod(new SendataAnalyticsMethodCell(
                'onMenuItemClick',
                '(Landroid/view/MenuItem;)Z',
                'androidx/appcompat/widget/Toolbar$OnMenuItemClickListener',
                'trackMenuItem',
                '(Landroid/view/MenuItem;)V',
                1, 1,
                [Opcodes.ALOAD]))
        addInterfaceMethod(new SendataAnalyticsMethodCell(
                'onClick',
                '(Landroid/content/DialogInterface;IZ)V',
                'android/content/DialogInterface$OnMultiChoiceClickListener',
                'trackDialog',
                '(Landroid/content/DialogInterface;I)V',
                1, 2,
                [Opcodes.ALOAD, Opcodes.ILOAD]))
        addInterfaceMethod(new SendataAnalyticsMethodCell(
                'onMenuItemClick',
                '(Landroid/view/MenuItem;)Z',
                'android/widget/PopupMenu$OnMenuItemClickListener',
                'trackMenuItem',
                '(Landroid/view/MenuItem;)V',
                1, 1,
                [Opcodes.ALOAD]))
        addInterfaceMethod(new SendataAnalyticsMethodCell(
                'onMenuItemClick',
                '(Landroid/view/MenuItem;)Z',
                'androidx/appcompat/widget/PopupMenu$OnMenuItemClickListener',
                'trackMenuItem',
                '(Landroid/view/MenuItem;)V',
                1, 1,
                [Opcodes.ALOAD]))
        addInterfaceMethod(new SendataAnalyticsMethodCell(
                'onMenuItemClick',
                '(Landroid/view/MenuItem;)Z',
                'android/support/v7/widget/PopupMenu$OnMenuItemClickListener',
                'trackMenuItem',
                '(Landroid/view/MenuItem;)V',
                1, 1,
                [Opcodes.ALOAD]))
        addInterfaceMethod(new SendataAnalyticsMethodCell(
                'onNavigationItemSelected',
                '(Landroid/view/MenuItem;)Z',
                'com/google/android/material/navigation/NavigationView$OnNavigationItemSelectedListener',
                'trackMenuItem',
                '(Landroid/view/MenuItem;)V',
                1, 1,
                [Opcodes.ALOAD]))
        addInterfaceMethod(new SendataAnalyticsMethodCell(
                'onNavigationItemSelected',
                '(Landroid/view/MenuItem;)Z',
                'android/support/design/widget/NavigationView$OnNavigationItemSelectedListener',
                'trackMenuItem',
                '(Landroid/view/MenuItem;)V',
                1, 1,
                [Opcodes.ALOAD]))
        addInterfaceMethod(new SendataAnalyticsMethodCell(
                'onNavigationItemSelected',
                '(Landroid/view/MenuItem;)Z',
                'android/support/design/widget/BottomNavigationView$OnNavigationItemSelectedListener',
                'trackMenuItem',
                '(Landroid/view/MenuItem;)V',
                1, 1,
                [Opcodes.ALOAD]))
        addInterfaceMethod(new SendataAnalyticsMethodCell(
                'onNavigationItemSelected',
                '(Landroid/view/MenuItem;)Z',
                'com/google/android/material/bottomnavigation/BottomNavigationView$OnNavigationItemSelectedListener',
                'trackMenuItem',
                '(Landroid/view/MenuItem;)V',
                1, 1,
                [Opcodes.ALOAD]))
    }

    static {
        addClassMethod(new SendataAnalyticsMethodCell(
                'performClick',
                '()Z',
                'androidx/appcompat/widget/ActionMenuPresenter$OverflowMenuButton',
                'trackViewOnClick',
                '(Landroid/view/View;)V',
                0, 1,
                [Opcodes.ALOAD]))

        addClassMethod(new SendataAnalyticsMethodCell(
                'performClick',
                '()Z',
                'android/support/v7/widget/ActionMenuPresenter$OverflowMenuButton',
                'trackViewOnClick',
                '(Landroid/view/View;)V',
                0, 1,
                [Opcodes.ALOAD]))

        addClassMethod(new SendataAnalyticsMethodCell(
                'performClick',
                '()Z',
                'android/widget/ActionMenuPresenter$OverflowMenuButton',
                'trackViewOnClick',
                '(Landroid/view/View;)V',
                0, 1,
                [Opcodes.ALOAD]))
    }

    static void addInterfaceMethod(SendataAnalyticsMethodCell sendataAnalyticsMethodCell) {
        if (sendataAnalyticsMethodCell != null) {
            INTERFACE_METHODS.put(sendataAnalyticsMethodCell.parent + sendataAnalyticsMethodCell.name + sendataAnalyticsMethodCell.desc, sendataAnalyticsMethodCell)
        }
    }

    static void addClassMethod(SendataAnalyticsMethodCell sendataAnalyticsMethodCell) {
        if (sendataAnalyticsMethodCell != null) {
            CLASS_METHODS.put(sendataAnalyticsMethodCell.parent + sendataAnalyticsMethodCell.name + sendataAnalyticsMethodCell.desc, sendataAnalyticsMethodCell)
        }
    }

    /**
     * Fragment中的方法
     */
    public final static HashMap<String, SendataAnalyticsMethodCell> FRAGMENT_METHODS = new HashMap<>()

    static {
        FRAGMENT_METHODS.put('onResume()V', new SendataAnalyticsMethodCell(
                'onResume',
                '()V',
                '',// parent省略，均为 android/app/Fragment 或 android/support/v4/app/Fragment
                'trackFragmentResume',
                '(Ljava/lang/Object;)V',
                0, 1,
                [Opcodes.ALOAD]))
        FRAGMENT_METHODS.put('setUserVisibleHint(Z)V', new SendataAnalyticsMethodCell(
                'setUserVisibleHint',
                '(Z)V',
                '',// parent省略，均为 android/app/Fragment 或 android/support/v4/app/Fragment
                'trackFragmentSetUserVisibleHint',
                '(Ljava/lang/Object;Z)V',
                0, 2,
                [Opcodes.ALOAD, Opcodes.ILOAD]))
        FRAGMENT_METHODS.put('onHiddenChanged(Z)V', new SendataAnalyticsMethodCell(
                'onHiddenChanged',
                '(Z)V',
                '',
                'trackOnHiddenChanged',
                '(Ljava/lang/Object;Z)V',
                0, 2,
                [Opcodes.ALOAD, Opcodes.ILOAD]))
        FRAGMENT_METHODS.put('onViewCreated(Landroid/view/View;Landroid/os/Bundle;)V', new SendataAnalyticsMethodCell(
                'onViewCreated',
                '(Landroid/view/View;Landroid/os/Bundle;)V',
                '',
                'onFragmentViewCreated',
                '(Ljava/lang/Object;Landroid/view/View;Landroid/os/Bundle;)V',
                0, 3,
                [Opcodes.ALOAD, Opcodes.ALOAD, Opcodes.ALOAD]))
    }

    /**
     * android.gradle 3.2.1 版本中，针对 Lambda 表达式处理
     */

    public final static HashMap<String, SendataAnalyticsMethodCell> LAMBDA_METHODS = new HashMap<>()
    //lambda 参数优化取样
    public final static ArrayList<SendataAnalyticsMethodCell> SAMPLING_LAMBDA_METHODS = new ArrayList<>()
    static {
        addLambdaMethod(new SendataAnalyticsMethodCell(
                'onClick',
                '(Landroid/view/View;)V',
                'Landroid/view/View$OnClickListener;',
                'trackViewOnClick',
                '(Landroid/view/View;)V',
                1, 1,
                [Opcodes.ALOAD]))
        SAMPLING_LAMBDA_METHODS.add(new SendataAnalyticsMethodCell(
                'onClick',
                '(Landroid/view/View;)V',
                'Landroid/view/View$OnClickListener;',
                'trackViewOnClick',
                '(Landroid/view/View;)V',
                1, 1,
                [Opcodes.ALOAD]))
        addLambdaMethod(new SendataAnalyticsMethodCell(
                'onCheckedChanged',
                '(Landroid/widget/CompoundButton;Z)V',
                'Landroid/widget/CompoundButton$OnCheckedChangeListener;',
                'trackViewOnClick',
                '(Landroid/view/View;)V',
                1, 1,
                [Opcodes.ALOAD]))
        addLambdaMethod(new SendataAnalyticsMethodCell(
                'onRatingChanged',
                '(Landroid/widget/RatingBar;FZ)V',
                'Landroid/widget/RatingBar$OnRatingBarChangeListener;',
                'trackViewOnClick',
                '(Landroid/view/View;)V',
                1, 1,
                [Opcodes.ALOAD]))
        addLambdaMethod(new SendataAnalyticsMethodCell(
                'onCheckedChanged',
                '(Landroid/widget/RadioGroup;I)V',
                'Landroid/widget/RadioGroup$OnCheckedChangeListener;',
                'trackRadioGroup',
                '(Landroid/widget/RadioGroup;I)V',
                1, 2,
                [Opcodes.ALOAD, Opcodes.ILOAD]))
        SAMPLING_LAMBDA_METHODS.add(new SendataAnalyticsMethodCell(
                'onCheckedChanged',
                '(Landroid/widget/RadioGroup;I)V',
                'Landroid/widget/RadioGroup$OnCheckedChangeListener;',
                'trackRadioGroup',
                '(Landroid/widget/RadioGroup;I)V',
                1, 2,
                [Opcodes.ALOAD, Opcodes.ILOAD]))
        addLambdaMethod(new SendataAnalyticsMethodCell(
                'onClick',
                '(Landroid/content/DialogInterface;I)V',
                'Landroid/content/DialogInterface$OnClickListener;',
                'trackDialog',
                '(Landroid/content/DialogInterface;I)V',
                1, 2,
                [Opcodes.ALOAD, Opcodes.ILOAD]))
        addLambdaMethod(new SendataAnalyticsMethodCell(
                'onItemClick',
                '(Landroid/widget/AdapterView;Landroid/view/View;IJ)V',
                'Landroid/widget/AdapterView$OnItemClickListener;',
                'trackListView',
                '(Landroid/widget/AdapterView;Landroid/view/View;I)V',
                1, 3,
                [Opcodes.ALOAD, Opcodes.ALOAD, Opcodes.ILOAD]))
        SAMPLING_LAMBDA_METHODS.add(new SendataAnalyticsMethodCell(
                'onItemClick',
                '(Landroid/widget/AdapterView;Landroid/view/View;IJ)V',
                'Landroid/widget/AdapterView$OnItemClickListener;',
                'trackListView',
                '(Landroid/widget/AdapterView;Landroid/view/View;I)V',
                1, 3,
                [Opcodes.ALOAD, Opcodes.ALOAD, Opcodes.ILOAD]))
        addLambdaMethod(new SendataAnalyticsMethodCell(
                'onGroupClick',
                '(Landroid/widget/ExpandableListView;Landroid/view/View;IJ)Z',
                'Landroid/widget/ExpandableListView$OnGroupClickListener;',
                'trackExpandableListViewOnGroupClick',
                '(Landroid/widget/ExpandableListView;Landroid/view/View;I)V',
                1, 3,
                [Opcodes.ALOAD, Opcodes.ALOAD, Opcodes.ILOAD]))
        addLambdaMethod(new SendataAnalyticsMethodCell(
                'onChildClick',
                '(Landroid/widget/ExpandableListView;Landroid/view/View;IIJ)Z',
                'Landroid/widget/ExpandableListView$OnChildClickListener;',
                'trackExpandableListViewOnChildClick',
                '(Landroid/widget/ExpandableListView;Landroid/view/View;II)V',
                1, 4,
                [Opcodes.ALOAD, Opcodes.ALOAD, Opcodes.ILOAD, Opcodes.ILOAD]))
        addLambdaMethod(new SendataAnalyticsMethodCell(
                'onTabChanged',
                '(Ljava/lang/String;)V',
                'Landroid/widget/TabHost$OnTabChangeListener;',
                'trackTabHost',
                '(Ljava/lang/String;)V',
                1, 1,
                [Opcodes.ALOAD]))
        addLambdaMethod(new SendataAnalyticsMethodCell(
                'onNavigationItemSelected',
                '(Landroid/view/MenuItem;)Z',
                'Lcom/google/android/material/navigation/NavigationView$OnNavigationItemSelectedListener;',
                'trackMenuItem',
                '(Landroid/view/MenuItem;)V',
                1, 1,
                [Opcodes.ALOAD]))
        addLambdaMethod(new SendataAnalyticsMethodCell(
                'onNavigationItemSelected',
                '(Landroid/view/MenuItem;)Z',
                'Landroid/support/design/widget/NavigationView$OnNavigationItemSelectedListener;',
                'trackMenuItem',
                '(Landroid/view/MenuItem;)V',
                1, 1,
                [Opcodes.ALOAD]))
        addLambdaMethod(new SendataAnalyticsMethodCell(
                'onNavigationItemSelected',
                '(Landroid/view/MenuItem;)Z',
                'Landroid/support/design/widget/BottomNavigationView$OnNavigationItemSelectedListener;',
                'trackMenuItem',
                '(Landroid/view/MenuItem;)V',
                1, 1,
                [Opcodes.ALOAD]))
        addLambdaMethod(new SendataAnalyticsMethodCell(
                'onNavigationItemSelected',
                '(Landroid/view/MenuItem;)Z',
                'Lcom/google/android/material/bottomnavigation/BottomNavigationView$OnNavigationItemSelectedListener;',
                'trackMenuItem',
                '(Landroid/view/MenuItem;)V',
                1, 1,
                [Opcodes.ALOAD]))
        addLambdaMethod(new SendataAnalyticsMethodCell(
                'onMenuItemClick',
                '(Landroid/view/MenuItem;)Z',
                'Landroid/widget/Toolbar$OnMenuItemClickListener;',
                'trackMenuItem',
                '(Landroid/view/MenuItem;)V',
                1, 1,
                [Opcodes.ALOAD]))
        addLambdaMethod(new SendataAnalyticsMethodCell(
                'onMenuItemClick',
                '(Landroid/view/MenuItem;)Z',
                'Landroid/support/v7/widget/Toolbar$OnMenuItemClickListener;',
                'trackMenuItem',
                '(Landroid/view/MenuItem;)V',
                1, 1,
                [Opcodes.ALOAD]))
        addLambdaMethod(new SendataAnalyticsMethodCell(
                'onMenuItemClick',
                '(Landroid/view/MenuItem;)Z',
                'Landroidx/appcompat/widget/Toolbar$OnMenuItemClickListener;',
                'trackMenuItem',
                '(Landroid/view/MenuItem;)V',
                1, 1,
                [Opcodes.ALOAD]))
        addLambdaMethod(new SendataAnalyticsMethodCell(
                'onClick',
                '(Landroid/content/DialogInterface;IZ)V',
                'Landroid/content/DialogInterface$OnMultiChoiceClickListener;',
                'trackDialog',
                '(Landroid/content/DialogInterface;I)V',
                1, 2,
                [Opcodes.ALOAD, Opcodes.ILOAD]))
        addLambdaMethod(new SendataAnalyticsMethodCell(
                'onMenuItemClick',
                '(Landroid/view/MenuItem;)Z',
                'Landroid/widget/PopupMenu$OnMenuItemClickListener;',
                'trackMenuItem',
                '(Landroid/view/MenuItem;)V',
                1, 1,
                [Opcodes.ALOAD]))
        addLambdaMethod(new SendataAnalyticsMethodCell(
                'onMenuItemClick',
                '(Landroid/view/MenuItem;)Z',
                'Landroidx/appcompat/widget/PopupMenu$OnMenuItemClickListener;',
                'trackMenuItem',
                '(Landroid/view/MenuItem;)V',
                1, 1,
                [Opcodes.ALOAD]))
        addLambdaMethod(new SendataAnalyticsMethodCell(
                'onMenuItemClick',
                '(Landroid/view/MenuItem;)Z',
                'Landroid/support/v7/widget/PopupMenu$OnMenuItemClickListener;',
                'trackMenuItem',
                '(Landroid/view/MenuItem;)V',
                1, 1,
                [Opcodes.ALOAD]))

        // Todo: 扩展
    }

    static void addLambdaMethod(SendataAnalyticsMethodCell sendataAnalyticsMethodCell) {
        if (sendataAnalyticsMethodCell != null) {
            LAMBDA_METHODS.put(sendataAnalyticsMethodCell.parent + sendataAnalyticsMethodCell.name + sendataAnalyticsMethodCell.desc, sendataAnalyticsMethodCell)
        }
    }
}