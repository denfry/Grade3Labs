   M S B A M L   `   `   `  ����  
Algos4Lab2    Algos4Lab2.MainWindow   5   	   R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35\ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    5   	   R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5   	     � Title$  
MainWindow��5   	   $	��485��6   $	��923��6)   .��5      �  � RowDefinitions 5   
   �� 5      $
��Auto �6   �� 5      $��* �6   �� 5	      $
��Auto �6   .+�5   
   �� $��0��6    �� Orientation$ 
Horizontal=�6"   $��5q�6;   .+�5      �� -   5         ��Name$ btnBuildTree��5      $%��Построить дерево��6+   $��5q�6a   �� 5      -   5        $ btnShowSelected��5      $2��,Показать выбранный узел��6.   $��5q�6n   �� 5      -   5        $ btnShowParent��5      $'��!Показать родителя��6,   $��5q�6d   5      R� 5   
   -   5         R�Name$ TreeView��5      $��1��6%   $��	5,5,503,0q�62   $	��354��6E   $	��Top=�6R   �� 5   
   -   5         ��Name$ txtMessages��5       �� 
IsReadOnly .  �� AcceptsReturn .  �� TextWrapping$
 Wrap=�6N   $��1��6b   $��0,10,24,187q�6o   $��Right=�6�   $	��445��6�   5      5      