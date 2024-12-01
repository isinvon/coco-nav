// store/mouseState.js
import { defineStore } from 'pinia';

export const useMouseState = defineStore('mouseState', {
 state: () => ({
   isMouseNearAnchor: false,
 }),
 actions: {
   setMouseNearAnchor(value) {
     this.isMouseNearAnchor = value;
   },
 },
});
