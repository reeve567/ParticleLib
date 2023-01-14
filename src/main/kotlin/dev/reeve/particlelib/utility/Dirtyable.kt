package dev.reeve.particlelib.utility

class Dirtyable<T>(initialValue: T) {
	var dirty = false
	var value: T = initialValue
		set(value) {
			if (field != value) {
				field = value
				dirty = true
				onDirty?.invoke()
			}
		}
	var onDirty: (() -> Unit)? = null
	
}