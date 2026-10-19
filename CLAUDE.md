# Vysion

NeoForge add-on for **Vivecraft** (the open-source VR mod for Minecraft Java Edition): a per-player
**hybrid VR input mode**. Keeps Vivecraft's headset rendering (stereoscopic view) and head tracking
active as normal, but suppresses Vivecraft's own motion-controller-driven movement/interaction and
uses plain keyboard+mouse as the primary input instead — the headset only ever rotates head/camera
look; mouse and keyboard control torso facing and interactions, so aiming follows the torso rather
than head gaze. Entirely client-side (`Dist.CLIENT`) — there is no server-side concept to this
feature at all.

**Not exclusive to any particular server** — a normal mod that works wherever it's installed,
exactly like every other mod. It has one optional cross-mod integration: when Kyngdoms'
**Audyble** (3D positional audio) is also present, listener orientation for audio panning should
follow real head tracking instead of the vanilla camera rotation — same optional-soft-dependency
shape every cross-mod integration in the Kyngdoms suite already uses (`ModList.get().isLoaded(...)`),
not a hard dependency.

**Architecture commitment, confirmed explicitly before building anything**: a separate NeoForge
add-on depending on Vivecraft's real public API (`org.vivecraft.api`) plus Mixin only where the
public API doesn't expose a clean hook — **never a fork of Vivecraft itself**. Lower maintenance,
stays compatible as Vivecraft updates, matches the "optional dependency on a sibling mod's API"
pattern the whole Kyngdoms suite already uses elsewhere.

## Context directory — read this first

`context/` is a **separate private repo** (https://github.com/CerealKlla/vysion-context), not part
of this one — listed in `.gitignore` here, cloned as a subdirectory for local convenience. If missing:

```
git clone https://github.com/CerealKlla/vysion-context.git context
```

- `context/design-document.md` — the authoritative design spec, including the real Vivecraft source
  research (exact classes/methods/line numbers for the Mixin plan) done before any code was written.
  Start here before touching anything input/rotation-related.
- `context/decisions.md` — dated log of decisions + pending facts.
- `context/classes/` — one short markdown file per implemented class, once there are any.

**Keep this system current as you work** — same discipline as every mod in the Kyngdoms suite.

## Status

**Scaffolded 2026-10-18** — NeoForge, Minecraft 26.1.2, Java 25, mirroring every other suite mod's
MDK setup. Real dependency confirmed and resolving: `maven.modrinth:vivecraft:26.1.2-1.3.15-neoforge`
(found via Vivecraft's own Modrinth versions API, not guessed — see context/decisions.md).
`VysionMod` is a placeholder client-setup log line only — no real hybrid-input logic exists yet.

**Real source research done before any implementation**, see `context/design-document.md` for the
full detail: Vivecraft's own `org.vivecraft.api` package was read directly from its real,
confirmed-canonical repo (`Vivecraft/VivecraftMod`, branch `Multiloader-26.2`) to find the actual
Mixin target (`VRPlayer.doPermanentLookOverride`, lines 691–762 in
`client_vr/gameplay/VRPlayer.java`) rather than guessing. `./gradlew build` green, dependency
resolution confirmed via `./gradlew dependencies`.

**Not yet done**: Mixin's actual NeoForge/ModDevGradle wiring (plugin/config setup — not yet
researched), the real Mixin classes themselves, the live-toggle keybind, and the Audyble
integration.
