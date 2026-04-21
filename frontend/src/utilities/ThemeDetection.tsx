import { useEffect, useState } from "react";

type Theme = "light" | "dark";

export const useSystemTheme = (): Theme => {
  const media = window.matchMedia("(prefers-color-scheme:dark)");
  const [theme, setTheme] = useState<Theme>(media.matches ? "dark" : "light");
  useEffect(() => {
    const changeListener = (event: MediaQueryListEvent) => {
      setTheme(event.matches ? "dark" : "light");
    };
    media.addEventListener("change", changeListener);
    return () => media.removeEventListener("change", changeListener);
  });
  return theme;
};
